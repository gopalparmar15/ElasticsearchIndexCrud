package com.prominentpixel;

import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;

import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;

public class QuestionIndex {
    TransportClient client=null;
    public static void main(String[] args) {
        QuestionIndex questionIndex=new QuestionIndex();
        try {
            questionIndex.initEStransportClinet();
            questionIndex.indexDocument();
            questionIndex.delete();
            questionIndex.indexSearch();
        }
        catch (Exception e){
            e.printStackTrace();
        }finally {
            //esExample.closeTransportClient(); //close transport client
        }
    }
    public boolean initEStransportClinet() throws UnknownHostException {

            // un-command this, if you have multiple node
            client = new PreBuiltTransportClient(Settings.EMPTY)
                    .addTransportAddress(new TransportAddress(InetAddress.getByName("localhost"), 9300));
            return true;

    }
    private boolean indexDocument(Data data) throws IOException {

            IndexResponse response = client.prepareIndex("question1", "question", String.valueOf(data.getId()))
                    .setSource(jsonBuilder()
                            .startObject()
                            .field("Question", data.getQuestion())
                            .field("Status", data.getStatus())
                            .field("Catagoryid", data.getCategoryid())
                            .field("Level",data.getLevel())
                            .field("Type",data.getType())
                            .endObject()
                    )
                    .get();
            return true;

    }
    public void indexDocument() throws IOException {
        Data data = new Data();
        data.setId(8);
        data.setQuestion("what is fathername");
        data.setStatus("active");
        data.setCategoryid("123456ebcd");
        data.setLevel("begginer");
        data.setType("single");
       boolean isIndexed = this.indexDocument(data);

    }
    public void update()
    {

    }
    public void delete()
    {
        DeleteResponse response=client.prepareDelete().setIndex("question1").setType("question").setId("100").execute().actionGet();
    }
    public void indexSearch()
    {
        SearchResponse response=client.prepareSearch("question1").setTypes("question").get();
        System.out.println(response);
    }

    public void closeTransportClient(){
        if(client!=null){
            client.close();
        }
    }
}

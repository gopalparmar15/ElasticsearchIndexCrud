package com.prominentpixel.crudIndex;

import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class DataSearchIndex {
    TransportClient client=null;
    public boolean connetionClient() throws UnknownHostException {
        client=new PreBuiltTransportClient(Settings.EMPTY)
                .addTransportAddress(new TransportAddress(InetAddress.getByName("localhost"),9300));
        return true;
    }
    public void searchIndex()
    {
       // SearchResponse searchResponse=client.prepareSearch().setIndices("question").setTypes("_doc").get();
        //System.out.println(searchResponse);
        GetResponse getResponse=client.prepareGet().setIndex("questions").setType("quetiondata").setId("1").execute().actionGet();
        System.out.println(getResponse);
    }
    public static void main(String[] args) throws UnknownHostException {
        DataSearchIndex dataSearchIndex=new DataSearchIndex();
        dataSearchIndex.connetionClient();
        dataSearchIndex.searchIndex();

    }
}

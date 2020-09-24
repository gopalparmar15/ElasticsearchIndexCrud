package com.prominentpixel.crudIndex;

import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

public class DataAddIndex {
    TransportClient  client=null;
    public static void main(String[] args) throws UnknownHostException {
        DataAddIndex question=new DataAddIndex();
        question.connectionClient();
        question.addDataIndex();

    }
    public boolean connectionClient() throws UnknownHostException {
        client = new PreBuiltTransportClient(Settings.EMPTY)
                .addTransportAddress(new TransportAddress(InetAddress.getByName("localhost"), 9300));
        return true;
    }
    public void addDataIndex()
    {
        Map<String,Object> document=new HashMap<String,Object>();
        document.put("question","what is jason");
        document.put("Status","InActive");
        document.put("Catagoryid","9935536657889");
        document.put("Level","begginer");
        document.put("Type","multi");
        IndexResponse response=client.prepareIndex().setIndex("questions").setType("quetiondata").setId("20").setSource(document).execute().actionGet();
    }
    public void closeTransportClient(){
        if(client!=null){
            client.close();
        }
    }

}

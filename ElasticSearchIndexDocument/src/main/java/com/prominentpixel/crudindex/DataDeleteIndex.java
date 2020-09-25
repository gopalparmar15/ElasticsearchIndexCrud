package com.prominentpixel.crudindex;

import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class DataDeleteIndex {
    TransportClient client;
    public boolean connetionClient()  {
        try {
            client = new PreBuiltTransportClient(Settings.EMPTY)
                    .addTransportAddress(new TransportAddress(InetAddress.getByName("localhost"), 9300));
            return true;
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
            return false;
        }

    }
    public void dataDeleteIndex()
    {
        DeleteResponse deleteResponse=client.prepareDelete().setIndex("questions").setType("quetiondata").setId("20").execute().actionGet();
    }
    public void closeClient(){
        if(client!=null){
            client.close();
        }
    }
    public static void main(String[] args) {
        DataDeleteIndex dataDeleteIndex=new DataDeleteIndex();
        try {
            dataDeleteIndex.connetionClient();
            dataDeleteIndex.dataDeleteIndex();
        }
        catch (Exception exception)
        {
         exception.printStackTrace();
        }
    }
}

package com.prominentpixel.crudIndex;

import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class DataDeleteIndex {
    TransportClient client=null;
    public boolean connetionClient() throws UnknownHostException {
       client =new PreBuiltTransportClient(Settings.EMPTY).addTransportAddress(new TransportAddress(InetAddress.getByName("localhost"),9300));
       return true;
    }
    public void dataDeleteIndex()
    {
        DeleteResponse deleteResponse=client.prepareDelete().setIndex("questions").setType("quetiondata").setId("20").execute().actionGet();
    }

    public static void main(String[] args) throws UnknownHostException {
        DataDeleteIndex dataDeleteIndex=new DataDeleteIndex();
        dataDeleteIndex.connetionClient();
        dataDeleteIndex.dataDeleteIndex();


    }
}

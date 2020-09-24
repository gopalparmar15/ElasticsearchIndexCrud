package com.prominentpixel.crudIndex;

import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

public class DataUpdateIndex {
    TransportClient client=null;
    public boolean connetionClient() throws UnknownHostException {
       client=new PreBuiltTransportClient(Settings.EMPTY)
               .addTransportAddress(new TransportAddress(InetAddress.getByName("localhost"),9300));
        return true;
    }
    public void updateIndex()
    {
        Map<String,Object> objectMap=new HashMap<String, Object>();
        objectMap.put("question","what is response");
        UpdateResponse response=client.prepareUpdate().setIndex("questions").setType("quetiondata").setId("13")
                .setDoc(objectMap).execute().actionGet();

    }

    public static void main(String[] args) throws UnknownHostException {
    DataUpdateIndex dataUpdateIndex=new DataUpdateIndex();
    dataUpdateIndex.connetionClient();
    dataUpdateIndex.updateIndex();
    }
}

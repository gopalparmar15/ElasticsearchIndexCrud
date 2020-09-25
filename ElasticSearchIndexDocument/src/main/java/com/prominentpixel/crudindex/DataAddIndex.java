package com.prominentpixel.crudindex;

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
    TransportClient client=null;
    public boolean connetionClient() {
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
    public void addDataIndex()
    {
        Map<String,Object> document=new HashMap<String,Object>();
        document.put("question","what is jason");
        document.put("Status","InActive");
        document.put("Catagoryid","9935536657889");
        document.put("Level","begginer");
        document.put("Type","multi");
        IndexResponse indexResponse=client.prepareIndex().setIndex("questions").setType("quetiondata").setId("20").setSource(document).execute().actionGet();
    }
    public void closeClient(){
        if(client!=null){
            client.close();
        }
    }
    public static void main(String[] args)  {
        DataAddIndex dataAddIndex=new DataAddIndex();
        try {
            dataAddIndex.connetionClient();
            dataAddIndex.addDataIndex();
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
        }
finally {
            dataAddIndex.closeClient();
        }
    }

}

package com.prominentpixel.crudindex;

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
        finally {

        }

    }
    public void updateIndex()
    {
        Map<String,Object> objectMap=new HashMap<String, Object>();
        objectMap.put("question","what is response");
        UpdateResponse updateResponse=client.prepareUpdate().setIndex("questions").setType("quetiondata").setId("13")
                .setDoc(objectMap).execute().actionGet();

    }
    public void closeClient(){
        if(client!=null){
            client.close();
        }
    }

    public static void main(String[] args)  {
    DataUpdateIndex dataUpdateIndex=new DataUpdateIndex();
    try {
        dataUpdateIndex.connetionClient();
        dataUpdateIndex.updateIndex();
    }
    catch (Exception exception)
    {
        exception.printStackTrace();
    }
finally {
        dataUpdateIndex.closeClient();
    }
    }
}

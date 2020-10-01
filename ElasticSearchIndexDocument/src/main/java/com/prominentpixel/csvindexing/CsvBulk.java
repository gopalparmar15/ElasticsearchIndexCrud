package com.prominentpixel.csvindexing;


import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.io.IOException;
import java.net.InetAddress;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CsvBulk {
    TransportClient client;
    public boolean  connectionClient()
    {
        try {
            client=new PreBuiltTransportClient(Settings.EMPTY).addTransportAddress(new TransportAddress(InetAddress.getByName("localhost"),9300));
            return true;
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
            return false;
        }
    }
    public void bulkIndex() throws IOException {
        BulkRequestBuilder requestBuilder=client.prepareBulk();
        List<String> lines= Files.readAllLines(Paths.get("/home/pp-5/files/quetion.csv"));
        Map<String,Object> objectMap=new HashMap<String, Object>();
        for (String str:lines)
        {
            str=str.replace("\"","");
            String[] data=str.split(",");
            objectMap.put("question",data[1]);
            objectMap.put("Status",data[2]);
            objectMap.put("Level",data[3]);
            objectMap.put("Type",data[4]);

            requestBuilder.add(client.prepareIndex("que","q",data[0]).setSource(objectMap));
        }
        BulkResponse responses=requestBuilder.execute().actionGet();
    }

    public static void main(String[] args) throws IOException {
        CsvBulk csvBulk=new CsvBulk();
        csvBulk.connectionClient();
        csvBulk.bulkIndex();

    }
}

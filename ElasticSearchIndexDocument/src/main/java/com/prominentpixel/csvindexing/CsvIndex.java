package com.prominentpixel.csvindexing;

import org.elasticsearch.action.index.IndexResponse;
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

public class CsvIndex {
    TransportClient client;
    public boolean ConnectionClient()
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
    public void csvIndex() throws IOException {

        List<String> result= Files.readAllLines(Paths.get("/home/pp-5/files/quetion.csv"));
        Map<String,Object> document=new HashMap<String, Object>();
        for (String s : result) {
            s = s.replace("\"", "");
            String[] data = s.split(",");
            document.put("question",data[1]);
            document.put("Status",data[2]);
            document.put("Level",data[3]);

            document.put("Type",data[4]);
           IndexResponse response=client.prepareIndex().setIndex("questiondatas").setType("csv").setId(data[0]).setSource(document).execute().actionGet();

        }


    }

    public static void main(String[] args) throws IOException {
        CsvIndex csvIndex=new CsvIndex();
        csvIndex.ConnectionClient();
        csvIndex.csvIndex();
    }
}

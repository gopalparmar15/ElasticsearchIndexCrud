package com.prominentpixel.csvindexing;

import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import java.io.IOException;
import java.net.InetAddress;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;

public class CsvIndexX {
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
    public void bulkIndexJason() throws IOException {

        BulkRequestBuilder requestBuilder=client.prepareBulk();
        List<String> lines= Files.readAllLines(Paths.get("/home/pp-5/files/quetion.csv"));
        for (String str:lines)
        {
            str=str.replace("\"","");
            String[] data=str.split(",");
            XContentBuilder xContentBuilder = jsonBuilder().startObject().field("question", data[1]).field("Status", data[2])
                    .field("Level", data[3]).field("Type", data[4]).endObject();
            requestBuilder.add(client.prepareIndex("gopal","g",data[0]).setSource(xContentBuilder));
        }
        BulkResponse responses=requestBuilder.execute().actionGet();
    }

    public static void main(String[] args) throws IOException {
        CsvIndexX csvIndexX=new CsvIndexX();
        csvIndexX.connectionClient();
        csvIndexX.bulkIndexJason();
    }
}

package com.prominentpixel;

import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.net.InetAddress;
import java.util.concurrent.ExecutionException;

public class CreateIndex {
    TransportClient client;
    public boolean connectionClient()
    {
        try
        {
          client=new PreBuiltTransportClient(Settings.EMPTY).addTransportAddress(new TransportAddress(InetAddress.getByName("localhost"),9300));
            return true;
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
            return false;
        }
    }
    public void indexCreate() throws ExecutionException, InterruptedException {
        CreateIndexRequest request=new CreateIndexRequest("studentdata");
        request.settings(Settings.builder().put("index.max_inner_result_window", 250)
                .put("index.write.wait_for_active_shards", 1)
                .put("index.query.default_field", "paragraph")
                .put("index.number_of_shards", 3)
                .put("index.number_of_replicas", 2));
        CreateIndexResponse response=client.admin().indices().create(request).get();
        System.out.println(request.index());
       ;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CreateIndex createIndex=new CreateIndex();
        createIndex.connectionClient();
        createIndex.indexCreate();


    }
}

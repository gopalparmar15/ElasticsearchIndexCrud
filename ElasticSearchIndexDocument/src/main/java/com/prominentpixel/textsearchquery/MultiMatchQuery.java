package com.prominentpixel.textsearchquery;

import org.apache.lucene.util.QueryBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.index.query.MultiMatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.net.InetAddress;

public class MultiMatchQuery {
    TransportClient client;
    public boolean connectionClient()
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
    public void multiMatchQuery()
    {
        MultiMatchQueryBuilder queryBuilder= QueryBuilders.multiMatchQuery("computer","name","batch");
        SearchResponse searchResponse=client.prepareSearch().setIndices("studentdata").setTypes("students").setQuery(queryBuilder).execute().actionGet();
        System.out.println(searchResponse.getHits().getTotalHits());
        System.out.println(searchResponse);
    }
    public void closeClient()
    {
        if (client!=null)
        {
            client.close();
        }
    }
    public static void main(String[] args) {
        MultiMatchQuery multiMatchQuery=new MultiMatchQuery();
        multiMatchQuery.connectionClient();
        multiMatchQuery.multiMatchQuery();
        multiMatchQuery.closeClient();

    }
}

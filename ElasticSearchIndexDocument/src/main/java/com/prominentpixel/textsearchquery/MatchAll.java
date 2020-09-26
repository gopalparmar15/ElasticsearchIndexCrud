package com.prominentpixel.textsearchquery;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.net.InetAddress;

public class MatchAll {
    TransportClient client;
    public boolean  connectionClint()
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
   public void matchAll()
    {
        QueryBuilder queryBuilder=QueryBuilders.matchAllQuery();
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
    MatchAll matchAll=new MatchAll();
    matchAll.connectionClint();
    matchAll.matchAll();
    }
}

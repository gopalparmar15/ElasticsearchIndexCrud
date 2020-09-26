package com.prominentpixel.compoundqueries;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.net.InetAddress;

public class BoolQuery {
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
    public void boolQuery()
    {
        BoolQueryBuilder boolQueryBuilder= QueryBuilders.boolQuery().must(QueryBuilders.
                termsQuery("name","jayal","ronak")).should(QueryBuilders.rangeQuery("Enroll").gte("160040107001").lte("160040107010"));
        SearchResponse searchResponse=client.prepareSearch().setIndices("studentdata").
                setTypes("students").setQuery(boolQueryBuilder).execute().actionGet();
        System.out.println(searchResponse.getHits().getTotalHits());
        System.out.println(searchResponse);
    }
    public void closeClient()
    {
        if(client!=null)
        {
            client.close();
        }
    }
    public static void main(String[] args) {
        BoolQuery boolQuery=new BoolQuery();
        boolQuery.connectionClient();
        boolQuery.boolQuery();
        boolQuery.closeClient();
    }
}

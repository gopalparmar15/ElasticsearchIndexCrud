package com.prominentpixel.aggregation.Bucket;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.net.InetAddress;

public class TermsAggregation {
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
    public void terms()
    {
        QueryBuilder queryBuilders=QueryBuilders.matchAllQuery();
        AggregationBuilder aggregation =
                AggregationBuilders.terms("agg").field("department");
        SearchResponse searchResponse=client.prepareSearch().setIndices("employees").setTypes("emp").setQuery(queryBuilders).addAggregation(aggregation).execute().actionGet();
        System.out.println(searchResponse);

    }
    public void closeClient(){
        if(client!=null){
            client.close();
        }
    }
    public static void main(String[] args) {
    TermsAggregation termsAggregation=new TermsAggregation();
    termsAggregation.connectionClient();
    termsAggregation.terms();
    termsAggregation.closeClient();
    }
}

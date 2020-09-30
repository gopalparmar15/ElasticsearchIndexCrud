package com.prominentpixel.aggregation.matrix;


import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.metrics.Min;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.net.InetAddress;

public class MinAggregation {
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
    public  void minAggregation()
    {
        QueryBuilder queryBuilder = QueryBuilders.matchAllQuery();
        AggregationBuilder aggregationBuilder= AggregationBuilders.min("aggs_min").field("age");
        SearchResponse response=client.prepareSearch().setIndices("employees").setTypes("emp").setQuery(queryBuilder)
                .addAggregation(aggregationBuilder).execute().actionGet();
        Min min=response.getAggregations().get("aggs_min");
        System.out.println(response);
        System.out.println(min);
    }
    public void closeClient(){
        if(client!=null){
            client.close();
        }
    }
    public static void main(String[] args) {
    MinAggregation minAggregation=new MinAggregation();
    minAggregation.connectionClient();
    minAggregation.minAggregation();
    minAggregation.closeClient();
    }
}

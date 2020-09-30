package com.prominentpixel.aggregation.matrix;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.metrics.Max;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.net.InetAddress;

public class MaxAggregation {
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
    public  void maxAggregation()
    {
        QueryBuilder queryBuilder = QueryBuilders.matchAllQuery();
        AggregationBuilder aggregationBuilder= AggregationBuilders.max("aggs_max").field("salary");
        SearchResponse response=client.prepareSearch().setIndices("employees").setTypes("emp").setQuery(queryBuilder)
                .addAggregation(aggregationBuilder).execute().actionGet();
        Max max=response.getAggregations().get("aggs_max");
        System.out.println(response);
        System.out.println(max);
    }
    public void closeClient(){
        if(client!=null){
            client.close();
        }
    }
    public static void main(String[] args) {
        MaxAggregation maxAggregation=new MaxAggregation();
        maxAggregation.connectionClient();
        maxAggregation.maxAggregation();
        maxAggregation.closeClient();
    }
}

package com.prominentpixel.aggregation.matrix;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.metrics.Avg;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.net.InetAddress;

public class AvgAggregation {
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
    public void average()
    {
        QueryBuilder queryBuilder= QueryBuilders.matchAllQuery();
        //avg method are user average of salary and part of marrix aggrigation
        AggregationBuilder aggregation = AggregationBuilders.avg("Aggs_Salary").field("salary");
        SearchResponse searchResponse=client.prepareSearch().setIndices("employees").setTypes("emp").addAggregation(aggregation).execute().actionGet();
        Avg avg=searchResponse.getAggregations().get("Aggs_Salary");
        System.out.println(searchResponse);
        System.out.println(avg);
    }
    public void closeClient(){
        if(client!=null){
            client.close();
        }
    }
    public static void main(String[] args) {
    AvgAggregation avgAggregation=new AvgAggregation();
    avgAggregation.connectionClient();
    avgAggregation.average();
    avgAggregation.closeClient();
    }
}

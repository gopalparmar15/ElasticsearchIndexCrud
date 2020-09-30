package com.prominentpixel.aggregation.matrix;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.metrics.Stats;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.net.InetAddress;

public class StatsAggregation {
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
    public void  stats()
    {
        QueryBuilder queryBuilder= QueryBuilders.matchAllQuery();
        AggregationBuilder aggregationBuilder= AggregationBuilders.stats("aggs_State").field("salary");
        SearchResponse searchResponse=client.prepareSearch().setIndices("employees").setTypes("emp").setQuery(queryBuilder).addAggregation(aggregationBuilder).execute().actionGet();
        Stats stats=searchResponse.getAggregations().get("aggs_State");
        System.out.println(searchResponse);
        System.out.println(stats);
    }

    public static void main(String[] args) {
        StatsAggregation statsAggregation=new StatsAggregation();
        statsAggregation.connectionClient();
        statsAggregation.stats();
    }
}

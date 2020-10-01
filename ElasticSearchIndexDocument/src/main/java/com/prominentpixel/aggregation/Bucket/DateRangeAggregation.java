package com.prominentpixel.aggregation.Bucket;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.range.Range;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.net.InetAddress;

public class DateRangeAggregation {
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
    public void dateRange()
    {
        QueryBuilder queryBuilders= QueryBuilders.matchAllQuery();
        AggregationBuilder aggregation =
                AggregationBuilders.dateRange("agg").field("joining").addUnboundedTo("2000").addRange("2000","2010");
        SearchResponse searchResponse=client.prepareSearch().setIndices("empyear").setTypes("year").setQuery(queryBuilders).addAggregation(aggregation).execute().actionGet();
        System.out.println(searchResponse);
       Range range=searchResponse.getAggregations().get("agg");
        System.out.println(range);
    }
    public void closeClient(){
        if(client!=null){
            client.close();
        }
    }

    public static void main(String[] args) {
        DateRangeAggregation dateRangeAggregation=new DateRangeAggregation();
        dateRangeAggregation.connectionClient();
        dateRangeAggregation.dateRange();
        dateRangeAggregation.closeClient();
    }
}

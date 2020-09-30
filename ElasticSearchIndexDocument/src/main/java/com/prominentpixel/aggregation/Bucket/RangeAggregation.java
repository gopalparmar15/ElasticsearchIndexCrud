package com.prominentpixel.aggregation.Bucket;

import com.prominentpixel.termquery.RangeQuery;
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

public class RangeAggregation {
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
    public void range()
    {
        QueryBuilder queryBuilders= QueryBuilders.matchAllQuery();
        AggregationBuilder aggregation =
                AggregationBuilders.range("agg").field("salary").addUnboundedTo(1).addRange(20000,30000);
        SearchResponse searchResponse=client.prepareSearch().setIndices("employees").setTypes("emp").setQuery(queryBuilders).addAggregation(aggregation).execute().actionGet();
        System.out.println(searchResponse);
        Range agg = searchResponse.getAggregations().get("agg");
        System.out.println(agg);
    }
    public void closeClient(){
        if(client!=null){
            client.close();
        }
    }
    public static void main(String[] args) {
        RangeAggregation rangeAggregation=new RangeAggregation();
        rangeAggregation.connectionClient();
        rangeAggregation.range();
        rangeAggregation.closeClient();
    }
}

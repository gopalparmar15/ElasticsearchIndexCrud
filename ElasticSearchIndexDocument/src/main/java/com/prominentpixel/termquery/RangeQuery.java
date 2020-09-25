package com.prominentpixel.termquery;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.RangeQueryBuilder;
import org.elasticsearch.index.query.TermsQueryBuilder;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.net.InetAddress;

public class RangeQuery {
    TransportClient client;
    public boolean connectionClinet()
    {
        try
        {
            client=new PreBuiltTransportClient(Settings.EMPTY).addTransportAddress(new TransportAddress(InetAddress.getByName("localhost"),9300));
            return  true;
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
            return false;
        }
    }
    public void rangeQuery()
    {

        RangeQueryBuilder rangeQueryBuilder=QueryBuilders.rangeQuery("stud_roll").gte("160040107024");
        SearchResponse searchResponse=client.prepareSearch("student").setTypes("stud")
                .setQuery(rangeQueryBuilder).setFrom(0).setSize(100).execute().actionGet();
        System.out.println(searchResponse.getHits().getTotalHits());
        System.out.println(searchResponse);

    }
    public void closeClient(){
        if(client!=null){
            client.close();
        }
    }
    public static void main(String[] args) {
    RangeQuery rangeQuery=new RangeQuery();
    rangeQuery.rangeQuery();
    rangeQuery.connectionClinet();
    rangeQuery.closeClient();
    }
}

package com.prominentpixel.termquery;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.index.query.ExistsQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermsQueryBuilder;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.net.InetAddress;

public class ExistsQueries {
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
    public void existsQueries()
    {

        ExistsQueryBuilder existsQueryBuilder=QueryBuilders.existsQuery("Level");
        SearchResponse searchResponse=client.prepareSearch("questions").setTypes("quetiondata")
                .setQuery(existsQueryBuilder).setFrom(0).setSize(100).execute().actionGet();
        System.out.println(searchResponse.getHits().getTotalHits());
        System.out.println(searchResponse);

    }

    public void closeClient(){
        if(client!=null){
            client.close();
        }
    }

    public static void main(String[] args) {
        ExistsQueries existsQueries=new ExistsQueries();
        existsQueries.connectionClinet();
        existsQueries.existsQueries();
        existsQueries.closeClient();
    }
}

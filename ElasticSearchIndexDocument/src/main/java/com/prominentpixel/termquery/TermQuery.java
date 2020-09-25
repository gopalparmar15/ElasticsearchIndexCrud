package com.prominentpixel.termquery;


import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.index.query.TermsQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.net.InetAddress;

public class TermQuery {
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
    public void termQuery()
    {

        TermQueryBuilder query = QueryBuilders.termQuery("Level", "export");
        SearchResponse searchResponse=client.prepareSearch("questions").setTypes("quetiondata").setQuery(query).setFrom(0).setSize(100).execute().actionGet();
        System.out.println(searchResponse.getHits().getTotalHits());
        System.out.println(searchResponse);

        }

    public void closeClient(){
        if(client!=null){
            client.close();
        }
    }
    public static void main(String[] args) {
        TermQuery termQuery=new TermQuery();
        termQuery.connectionClinet();
        termQuery.termQuery();
        termQuery.closeClient();
    }

}

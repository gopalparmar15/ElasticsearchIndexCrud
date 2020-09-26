package com.prominentpixel.textsearchquery;



import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.net.InetAddress;

public class MatchQuery {
    TransportClient client;
    public void connectionClient()
    {
        try {
            client= new PreBuiltTransportClient(Settings.EMPTY).addTransportAddress(new TransportAddress(InetAddress.getByName("localhost"),9300));
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
        }

    }
    public void match()
    {
        QueryBuilder queryBuilder= QueryBuilders.matchQuery("name","gopal");
        SearchResponse searchResponse=client.prepareSearch().setIndices("studentdata").setTypes("students").setQuery(queryBuilder).execute().actionGet();
        System.out.println(searchResponse.getHits().getTotalHits());
        System.out.println(searchResponse);
    }

    public static void main(String[] args) {
        MatchQuery matchQuery=new MatchQuery();
        matchQuery.connectionClient();
        matchQuery.match();
    }
}

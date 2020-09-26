package com.prominentpixel.textsearchquery;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.index.query.MatchPhraseQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.net.InetAddress;

public class PhraseSearch {
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
    public void phraseSearch()
    {
        MatchPhraseQueryBuilder queryBuilder= QueryBuilders.matchPhraseQuery("rollno","gopal parmar");
        SearchResponse searchResponse=client.prepareSearch().setIndices("studentdata").setTypes("students").setQuery(queryBuilder).execute().actionGet();
        System.out.println("hit count"+searchResponse.getHits().getTotalHits());
        System.out.println(searchResponse);
    }
    public void closeClient(){
        if(client!=null){
            client.close();
        }
    }
    public static void main(String[] args) {
        PhraseSearch phraseSearch=new PhraseSearch();
        phraseSearch.connectionClient();
        phraseSearch.phraseSearch();
        phraseSearch.closeClient();
    }
}

package com.prominentpixel;

import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.net.InetAddress;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CrudIndexApi {
    TransportClient client;
    Scanner scanner=new Scanner(System.in);
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
    public  void insertIndex()
    {


        System.out.print("id:");
        String id=scanner.nextLine();
        System.out.print("name:");
        String name=scanner.nextLine();
        System.out.print("age:");
        String age=scanner.nextLine();
        System.out.print("collage:");
        String collage=scanner.nextLine();
        Map<String,Object> objectMap=new HashMap<>();
        objectMap.put("name",name);
        objectMap.put("age",age);
        objectMap.put("collage",collage);
        IndexResponse response=client.prepareIndex().setIndex("studentinfo").setType("student").setId(id).setSource(objectMap).execute().actionGet();

    }
    public void deleteIndex()
    {
        System.out.print("delete Index id:");
        String id=scanner.nextLine();
        DeleteResponse response=client.prepareDelete().setIndex("studentinfo").setType("student").setId(id).execute().actionGet();
    }
    public  void upDateIndex()
    {


        System.out.print("id:");
        String id=scanner.nextLine();
        System.out.print("name:");
        String name=scanner.nextLine();
        System.out.print("age:");
        String age=scanner.nextLine();
        System.out.print("collage:");
        String collage=scanner.nextLine();
        Map<String,Object> objectMap=new HashMap<>();
        objectMap.put("name",name);
        objectMap.put("age",age);
        objectMap.put("collage",collage);
        UpdateResponse updateResponse=client.prepareUpdate().setIndex("studentinfo").setType("student").setId(id).setDoc(objectMap).execute().actionGet();

    }
    public void getIndex()
    {
        System.out.print("id:");
        String id=scanner.nextLine();
        GetResponse getResponse=client.prepareGet().setIndex("studentinfo").setType("student").setId(id).execute().actionGet();
        System.out.println(getResponse);
    }
    public static void main(String[] args) {
        CrudIndexApi crudIndexApi=new CrudIndexApi();
        crudIndexApi.connectionClient();
        Scanner scanner=new Scanner(System.in);
        System.out.println("1.Insert index");
        System.out.println("2.Delete index");
        System.out.println("3.Update index");
        System.out.println("4.Get index");
        System.out.print("enter value:");
        int str=scanner.nextInt();
        switch (str)
        {
            case 1:
                crudIndexApi.insertIndex();
                break;
            case 2:
                crudIndexApi.deleteIndex();
                break;
            case 3:
                crudIndexApi.upDateIndex();
                break;
            case 4:
                crudIndexApi.getIndex();
                break;
        }
    }
}

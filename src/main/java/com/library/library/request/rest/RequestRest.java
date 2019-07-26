package com.library.library.request.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.library.library.services.Book;


import com.library.library.request.soap.Exception_Exception;
import com.library.library.request.soap.RequestSoap;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import java.io.IOException;
import java.net.MalformedURLException;

public class RequestRest {
    Client client = Client.create();
    String urlClient = "http://localhost:8080/ServerRest_war/service/search/search";

    public Book postRequestBook(Book book) throws MalformedURLException {

        String url = urlClient;
        WebResource webResource = client.resource ( url );
        return mapperBook ( webResource, book, false);

    }


    public Book mapperBook(WebResource webResource, Book book, Boolean put)  {

        ObjectMapper mapper = new ObjectMapper();
        String inputData = null;
        ClientResponse response = null;

        try {
            inputData = mapper.writeValueAsString(book);
            System.out.println(inputData);
        } catch (JsonProcessingException e) {
            e.printStackTrace ( );
        }
        if (put){
            response = webResource.type ( "application/json" ).put ( ClientResponse.class, inputData );
            if (response.getStatus ( ) != 200) {
                throw new RuntimeException ( "HTTP Error: " + response.getStatus ( ) );
            }
        }
        else {
            response = webResource.type ( "application/json" ).post ( ClientResponse.class, inputData );
            if (response.getStatus ( ) != 200) {
                throw new RuntimeException ( "HTTP Error: " + response.getStatus ( ) );
            }
        }
        return getBookInfo ( webResource, response );
    }

    public Book getBookInfo(WebResource webResource, ClientResponse response)  {

        Book b = new Book ();
        if(response.getStatus()!=200){
            throw new RuntimeException("HTTP Error: "+ response.getStatus());
        }
        try {
            ObjectMapper objectMapper = new ObjectMapper ( );
            Book [] arrayBook = objectMapper.readValue (response.getEntity ( String.class ),Book[].class);

            for (Book jBook : arrayBook ) {
                System.out.println ( "------------------------------------------------" );
                System.out.println ("ID: " + jBook.getAuthor () + " / " + "Title: " + jBook.getTitle ());

                b.setIsbn(jBook.getIsbn());
                b.setTitle(jBook.getTitle());
                b.setAuthor(jBook.getAuthor());
                b.setDescription(jBook.getDescription());
                b.setIsbn(jBook.getIsbn());
                b.setPublishingDate(jBook.getPublishingdate());
                b.setSummary(jBook.getSummary());
                try {
                    b.setPublisher(new RequestSoap().getPublisher(jBook.getPublisher()));
                    System.out.println(new RequestSoap().getPublisher(jBook.getPublisher()));
                } catch (Exception_Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace ( );
        }
        System.out.println ( "------------------------------------------------" );
        return b;
    }
}

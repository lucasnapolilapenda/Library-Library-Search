package com.library.library.request.rest;

/**
 *
 * @author Lucas Napoli
 * V1.0
 * Library Microservices
 * Service search Consolidator
 */

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.library.library.services.Book;


import com.library.library.request.soap.Exception_Exception;
import com.library.library.request.soap.RequestSoap;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import sun.misc.BASE64Encoder;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;

/**
 * Request service to the Book Microservice REST
 */

public class RequestRest {
    Client client = Client.create();
    String urlClient = "http://localhost:8091/books/search/list";

    public ArrayList<Book> postRequestBook(Book book) throws MalformedURLException {

        String url = urlClient;
        WebResource webResource = client.resource ( url );
        return mapperBook ( webResource, book);

    }

    /**
     * Mapper Book
     * @param webResource WebResource
     * @param book Book Object
     * @return ArrayList of Books
     */


    public ArrayList<Book> mapperBook(WebResource webResource, Book book)  {

        ObjectMapper mapper = new ObjectMapper();
        String inputData = null;
        ClientResponse response = null;

        try {
            inputData = mapper.writeValueAsString(book);
            System.out.println(inputData);
        } catch (JsonProcessingException e) {
            System.out.println("Error: " + e);
        }

        response = webResource.type ( "application/json" )
                .header("Authorization", "Basic " + securityEncoder())
                .post ( ClientResponse.class, inputData );

        if (response.getStatus ( ) != 200) {
            throw new RuntimeException ( "HTTP Error: " + response.getStatus ( ) );
        }

        if (response == null) {
            System.out.println("Check app credentials configuration");
        }

        return getBookInfo (  response );
    }


    /**
     * Mapper contructor info
     * @param response from response
     * @return ArrayList of Books
     */

    public ArrayList<Book> getBookInfo( ClientResponse response)  {
        ArrayList<Book> bookArrayList = new ArrayList<>();

        if(response.getStatus()!=200){
            throw new RuntimeException("HTTP Error: "+ response.getStatus());
        }
        try {
            ObjectMapper objectMapper = new ObjectMapper ( );
            Book [] arrayBook = objectMapper.readValue (response.getEntity ( String.class ),Book[].class);

            for (Book jBook : arrayBook ) {
                System.out.println ( "------------------------------------------------" );
                System.out.println ("ID: " + jBook.getAuthor () + " / " + "Title: " + jBook.getTitle ());
                Book b = new Book ();
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
                    System.out.println("Error: " + e);
                }
                bookArrayList.add(b);
            }
        } catch (IOException e) {
            System.out.println("Error: " + e);
        }
        System.out.println ( "------------------------------------------------" );
        return bookArrayList;
    }

    /**
     * String Credential constructor
     * @return String with credentials
     */

    private String securityEncoder (){
        String userPassword = "book-service:book-service-secure-password-1234";
        String authStringEnc = new BASE64Encoder().encode(userPassword.getBytes());
        return authStringEnc;
    }
}

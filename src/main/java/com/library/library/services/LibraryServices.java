package com.library.library.services;

/**
 *
 * @author Lucas Napoli
 * V1.0
 * Library Microservices
 * Service search Consolidator
 */

import com.library.library.request.rest.RequestRest;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

import java.net.MalformedURLException;
import java.util.ArrayList;

/**
 *Class to manage services
 */

@Path( "/search" )
public class LibraryServices {


    @Context
    private UriInfo context;




    public LibraryServices() {
    }


    /**
     * Service call book info with publisher
     * @param authString authorization credentials
     * @return List of books with publisher
     *
     */

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    @Path( "/search" )
    public ArrayList<Book> bookCreation (Book book, @HeaderParam("authorization") String authString)  {
        try {
            if (CredentialsManager.getInstance(context).isUserAuthenticated(authString)) {
                return new RequestRest().postRequestBook(book);
            } else {
                System.out.println("User or Password does not match");
            }
        }catch (MalformedURLException e) {
            System.out.println("Error: " + e);
        }
        return null;
    }

    /**
     * Service to validate credentials
     * @param authString authorization credentials
     * @return Message to the user with the request status
     *
     */

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    @Path( "/auth" )
    public Messages credentialValidation (@HeaderParam("authorization") String authString) {
        if (CredentialsManager.getInstance(context).isUserAuthenticated(authString)) {
            Messages message = new Messages();
            message.setAlert("thanks for your credentials");
            message.setStatus("OK");
            return message;

        } else {
            Messages message = new Messages();
            message.setAlert("Please, check your credentials");
            message.setStatus("error");
            return message;
        }
    }
}

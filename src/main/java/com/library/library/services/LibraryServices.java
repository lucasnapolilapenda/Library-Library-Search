package com.library.library.services;

import com.library.library.request.rest.RequestRest;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

import java.net.MalformedURLException;
import java.util.ArrayList;



@Path( "/search" )
public class LibraryServices {


    @Context
    private UriInfo context;


    public LibraryServices() {
    }

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

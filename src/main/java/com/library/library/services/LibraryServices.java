package com.library.library.services;

import com.library.library.request.rest.RequestRest;
import sun.misc.BASE64Decoder;

import javax.servlet.ServletContext;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

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
    public ArrayList<Book> bookCreation (Book book, @HeaderParam("authorization") String authString) throws Exception {
        System.out.println(CredentialsManager.getInstance(context).isUserAuthenticated(authString));
        return new RequestRest().postRequestBook(book);
    }

}

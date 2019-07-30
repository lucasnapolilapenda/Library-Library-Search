package com.library.library.services;

import sun.misc.BASE64Decoder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.concurrent.ConcurrentHashMap;
import javax.servlet.ServletContext;
import javax.ws.rs.core.UriInfo;

/**
 *
 * @lucasnapoli
 */
public class BookRepository {

    private ConcurrentHashMap<Integer, Book> map;

    private BookRepository() {
        map = new ConcurrentHashMap<Integer, Book>();
    }


    private static BookRepository instance = null;


    public static BookRepository getInstance(UriInfo context) {
        return instance == null && context != null ?
                (instance = new BookRepository()) : instance;
    }

    public void bookCreation(Book b) {
        map.put(1, b);
    }


}


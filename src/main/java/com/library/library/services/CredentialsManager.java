package com.library.library.services;

/**
 *
 * @author Lucas Napoli
 * V1.0
 * Library Microservices
 * Service search Consolidator
 */

import sun.misc.BASE64Decoder;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

import java.io.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * Class to manage creentials
 */


public class CredentialsManager  {

    @Context
    UriInfo context;

    private static CredentialsManager instance = null;

    /**
     * Instance to authenticate
     * @param context URI
     * @return Intance Intance
     */


    public static CredentialsManager getInstance(UriInfo context) {
        return instance == null && context != null ?
                (instance = new CredentialsManager()) : instance;
    }

    /**
     * Authentication Process decode
     * @param authString String to authenticate
     * @return Boolean if the auth is OK
     */

    public boolean isUserAuthenticated(String authString){

        String decodedAuth = "";
        class Auth {
            private String user;
            private String password;
        }
        // Header is in the format "Basic 5tyc0uiDat4"
        // We need to extract data before decoding it back to original string
        String[] authParts = authString.split("\\s+");
        String authInfo = authParts[1];
        // Decode the data back to original string
        byte[] bytes = null;
        try {
            bytes = new BASE64Decoder().decodeBuffer(authInfo);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            System.out.println("Error: " + e);
        }
        decodedAuth = new String(bytes);
        ConcurrentHashMap<Integer, Auth> credentials = new ConcurrentHashMap<Integer, Auth>();

        String fileName = "src/main/webapp/WEB-INF/users.txt";
        InputStream in = null;
        try {
            in = new FileInputStream(fileName);
        } catch (FileNotFoundException e) {
            System.out.println("Error: " + e);
        }
        int id = 0;
        if (in != null) {
            try{
                InputStreamReader isr = new InputStreamReader(in);
                BufferedReader reader = new BufferedReader(isr);
                String record = null;
                while ((record = reader.readLine()) != null) {
                    String [] credArray = record.split(":");
                    Auth auth = new Auth();
                    auth.user = credArray [0];
                    auth.password = credArray [1];
                    id++;
                    credentials.put(id, auth);
                    System.out.println(auth.user + auth.password);
                }
            }catch (IOException e) {
                System.out.println("error: " + e);
            }
        }
        String [] userCredentials = decodedAuth.split(":");

        for (int i = 1; i < credentials.size() ; i++) {
            if (credentials.get(i).user.equals(userCredentials[0]) && credentials.get(i).password.equals(userCredentials[1])) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }
}


package com.library.library.services;

/**
 *
 * @author Lucas Napoli
 * V1.0
 * Library Microservices
 * Service search Consolidator
 */

/**
 *Class to manage Messages
 */

public class Messages {

    String alert;
    String status;

    /**
     *@param status String
     *
     */


    public void setStatus(String status) {
        this.status = status;
    }

    /**
     *@param alert String
     *
     */

    public void setAlert(String alert) {
        this.alert = alert;
    }
}

package com.library.library.services;

import java.net.InetSocketAddress;
import javax.ws.rs.ext.RuntimeDelegate;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

public class Publish {

    private static final int port = 9080;
    private static final String uri = "/LibrarySearchLibrary_war/";
    private static final String url = "http://localhost:" + port + uri;


    private HttpServer getServer() {
        HttpServer server = null;
        int backlog = 8;
        try {
            server = HttpServer.create(new InetSocketAddress("localhost", port), backlog);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return server;
    }

    public static void main(String[ ] args) {
        new Publish().publish();
    }
    private void publish() {
        HttpServer server = getServer();
        HttpHandler requestHandler =
                RuntimeDelegate.getInstance().createEndpoint(new AppConfig(), HttpHandler.class);
        server.createContext(uri, requestHandler);
        server.start();
        msg(server);
    }
    private void msg(HttpServer server) {
        String out = "Publishing RestfulAdage on " + url + ". Hit any key to stop.";
        System.out.println(out);
        try {
            System.in.read();
        } catch(Exception e) { }
        server.stop(0); // normal termination
    }
}

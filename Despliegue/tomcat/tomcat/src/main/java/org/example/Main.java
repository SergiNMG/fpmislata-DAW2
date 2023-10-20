package org.example;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

public class Main {
    public static int tcpPortNumber=8080;

    public static void main(String[] args) throws Exception {
        HttpServer httpServer = HttpServer.create(new InetSocketAddress(tcpPortNumber), 0);
        httpServer.createContext("/", new RootHttpHandler());
        httpServer.start();
        System.out.println("El servidor ha arrancado corectamente en el puerto:"+tcpPortNumber);
    }

    static class RootHttpHandler implements HttpHandler {

        @Override
        public void handle(HttpExchange httpExchange) throws IOException {

            //Crear un HTML con la direccion y el puerto tanto origen como destino
            StringBuilder html = new StringBuilder();
            html.append("<html><body>");
            html.append("<p>Direccion Local ==> "+httpExchange.getLocalAddress().getHostString()+ "&nbsp;&nbsp;:&nbsp;&nbsp;" + httpExchange.getLocalAddress().getPort()+"</p>");
            html.append("<p>Direccion Remota ==> "+httpExchange.getRemoteAddress().getHostString()+ "&nbsp;&nbsp;:&nbsp;&nbsp;" + httpExchange.getRemoteAddress().getPort()+"</p>");
            html.append("</body></html>");


            //Enviar el HTML por TCP
            httpExchange.getResponseHeaders().add("Content-Type", "text/html");
            httpExchange.sendResponseHeaders(200, html.length());
            OutputStream outputStream = httpExchange.getResponseBody();
            outputStream.write(html.toString().getBytes());
            outputStream.close();
        }
    }
}
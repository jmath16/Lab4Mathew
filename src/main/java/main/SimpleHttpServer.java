package main;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;

public class SimpleHttpServer {

    public static void main(String[] args) throws Exception {
        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
        server.createContext("/pizza", new MyHandler());
        server.setExecutor(null);
        server.start();

        System.out.println("Server started on http://localhost:8000/pizza");
    }

    static class MyHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            String response;

            if ("POST".equalsIgnoreCase(exchange.getRequestMethod())) {
                InputStream inputStream = exchange.getRequestBody();
                String json = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);

                Gson gson = new Gson();
                Pizza pizza = gson.fromJson(json, Pizza.class);

                System.out.println("Received JSON: " + json);
                System.out.println("Converted Pizza Object: " + pizza);

                response = "Server received pizza: " + pizza.toString();
            } else {
                response = "Use POST to send pizza JSON";
            }

            exchange.sendResponseHeaders(200, response.getBytes().length);
            OutputStream os = exchange.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }
}

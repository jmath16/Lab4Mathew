/** Project: Lab4 Pizza
 * Purpose Details: This program sends and receives Pizza objects using RabbitMQ and Web Services
 * Course: IST 242
 * Author: Jason Mathew
 * Date Developed: 3/28/26
 * Last Date Changed: 3/31/26
 * Rev: 1.0

 */

package main;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.OutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class WebServiceCaller {
    public static void main(String[] args) {
        try {
            String url = "http://localhost:8000/pizza";

            Pizza pizza = new Pizza("Medium", "Stuffed", "Cheese", 14.50);
            Gson gson = new Gson();
            String json = gson.toJson(pizza);

            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json");
            con.setDoOutput(true);

            try (OutputStream os = con.getOutputStream()) {
                byte[] input = json.getBytes();
                os.write(input, 0, input.length);
            }

            int responseCode = con.getResponseCode();
            System.out.println("Response Code: " + responseCode);

            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            System.out.println("Sent JSON: " + json);
            System.out.println("Response: " + response.toString());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

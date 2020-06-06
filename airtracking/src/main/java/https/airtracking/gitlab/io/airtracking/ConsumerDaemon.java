/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package https.airtracking.gitlab.io.airtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import static java.lang.Thread.sleep;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/**
 *
 * @author jarturcosta
 */
public class ConsumerDaemon extends Thread {
    
    @Override
    public void run() {
        while (true) {
            try {
                String message = FlightStateDeserializer.deserialize(getAllStates()).toString();
                sendPost(message);
                System.out.println("Consumed new data from OpenSky");
                
                Thread.sleep(120000);
            } catch (IOException ex) {
                //Logger.getLogger(ConsumerDaemon.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                //Logger.getLogger(ConsumerDaemon.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public String getAllStates() throws MalformedURLException, IOException {
        URL url = new URL("https://opensky-network.org/api/states/all");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");

        int status = con.getResponseCode();

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuilder content = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();
        

        return content.toString();

    }
    
    private void sendPost(String body) throws Exception {

        HttpPost post = new HttpPost("http://192.168.160.103:9069/flightstates/");
        //HttpPost post = new HttpPost("http://localhost:8005/flightstates/");


        StringEntity entity = new StringEntity(body);
        post.setHeader("Accept", "application/json");
        post.setHeader("Content-type", "application/json");
        post.setEntity(entity);

        try (CloseableHttpClient httpClient = HttpClients.createDefault();
                CloseableHttpResponse response = httpClient.execute(post)) {

            System.out.println(EntityUtils.toString(response.getEntity()));
        }

    }
    
}

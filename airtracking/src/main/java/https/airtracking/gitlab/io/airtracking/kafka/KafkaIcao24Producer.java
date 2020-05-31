/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package https.airtracking.gitlab.io.airtracking.kafka;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

/**
 *
 * @author jarturcosta
 */
public class KafkaIcao24Producer{
    
    private final KafkaProducer producer;
    private String topic;
    private Boolean isAsync;
    public static final String KAFKA_SERVER_URL = "localhost";
    public static final int KAFKA_SERVER_PORT = 9092;
    public static final String CLIENT_ID = "SampleProducer";
    public static int messageNo = 0;

    
    
    public KafkaIcao24Producer(String topic, Boolean isAsync) {
        Properties properties = new Properties();
        properties.put("bootstrap.servers", KAFKA_SERVER_URL + ":" + KAFKA_SERVER_PORT);
        properties.put("client.id", CLIENT_ID);
        properties.put("key.serializer", "org.apache.kafka.common.serialization.IntegerSerializer");
        properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        producer = new KafkaProducer(properties);
        this.topic = topic;
        this.isAsync = isAsync;
    }
    
    
    private void sendPost(String body) throws Exception {

        HttpPost post = new HttpPost("http://192.168.160.103:9069/flightstates/");

        StringEntity entity = new StringEntity(body);
        post.setHeader("Accept", "application/json");
        post.setHeader("Content-type", "application/json");
        post.setEntity(entity);

        try (CloseableHttpClient httpClient = HttpClients.createDefault();
             CloseableHttpResponse response = httpClient.execute(post)) {

            System.out.println(EntityUtils.toString(response.getEntity()));
        }

    }
    
    public static String getAllStates() throws MalformedURLException, IOException {
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
    
    public void sendMessage (String icao24, int messageNo) throws InterruptedException, ExecutionException {
        
        long startTime = System.currentTimeMillis();
        if (isAsync) { // Send asynchronously
            

            producer.send(new ProducerRecord(topic,icao24)).get();
        } else { // Send synchronously
            try {
                producer.send(new ProducerRecord(topic,messageNo,
                        icao24)).get();

                
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
                // handle the exception
            }
        }
        messageNo++;
    }
    
    
class DemoCallBack implements Callback {
    private final long startTime;
    private final int key;
    private final String message;
    public DemoCallBack(long startTime, int key, String message) {
        this.startTime = startTime;
        this.key = key;
        this.message = message;
    }
    /**
     * onCompletion method will be called when the record sent to the Kafka Server has been acknowledged.
     *
     * @param metadata  The metadata contains the partition and offset of the record. Null if an error occurred.
     * @param exception The exception thrown during processing of this record. Null if no error occurred.
     */
    public void onCompletion(RecordMetadata metadata, Exception exception) {
        long elapsedTime = System.currentTimeMillis() - startTime;
        if (metadata != null) {
            System.out.println(
                    "message(" + key + ", " + message + ") sent to partition(" + metadata.partition() +
                    "), " +
                    "offset(" + metadata.offset() + ") in " + elapsedTime + " ms");
        } else {
            exception.printStackTrace();
        }
    }
}
    
}

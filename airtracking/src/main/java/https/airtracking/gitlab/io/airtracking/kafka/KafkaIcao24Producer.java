/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package https.airtracking.gitlab.io.airtracking.kafka;


import java.util.Properties;
import java.util.concurrent.ExecutionException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.beans.factory.annotation.Value;

/**
 *
 * @author jarturcosta
 */
public class KafkaIcao24Producer{
    
    private final KafkaProducer producer;
    private String topic;
    private Boolean isAsync;
    public static final String CLIENT_ID = "SampleProducer";
    public static int messageNo = 0;
    //@Value("${kafka.server}")
    //private String BOOTSTRAP_SERVERS;

    
    
    public KafkaIcao24Producer(String topic, Boolean isAsync) {
        Properties properties = new Properties();
        properties.put("bootstrap.servers", "192.168.160.103:9092");
        //properties.put("bootstrap.servers", "localhost:9092");

        properties.put("client.id", CLIENT_ID);
        properties.put("key.serializer", "org.apache.kafka.common.serialization.IntegerSerializer");
        properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        this.producer = new KafkaProducer(properties);
        this.topic = topic;
        this.isAsync = isAsync;
    }
    

    
    
    public void sendMessage (String icao24, int messageNo) throws InterruptedException, ExecutionException {
        
        long startTime = System.currentTimeMillis();
        if (isAsync) { // Send asynchronously
            

            producer.send(new ProducerRecord(topic,icao24)).get();
        } else { // Send synchronously
            try {
                producer.send(new ProducerRecord(topic,
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

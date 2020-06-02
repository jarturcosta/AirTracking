/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package stats_service;



import com.google.gson.Gson;
import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.common.serialization.LongDeserializer;
import org.apache.kafka.common.serialization.StringDeserializer;


import java.util.Collections;
import java.util.Properties;
import models.FlightStateMessage;
import models.FlightStats;
import org.apache.kafka.common.serialization.IntegerDeserializer;
/**
 *
 * @author jarturcosta
 */
public class KafkaFlightStateConsumer extends Thread {
    private final static String TOPIC = "STATS_REQ";
    private final static String BOOTSTRAP_SERVERS =
            "192.168.160.103:9021";

    public KafkaFlightStateConsumer() {
    }
    
    
    
     private static Consumer<Long, String> createConsumer() {
      final Properties props = new Properties();
      props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,
                                  BOOTSTRAP_SERVERS);
      props.put(ConsumerConfig.GROUP_ID_CONFIG,
                                  "KafkaStatsConsumer");
      props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
              IntegerDeserializer.class.getName());
      props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
              StringDeserializer.class.getName());
      // Create the consumer using props.
      final Consumer<Long, String> consumer =
                                  new KafkaConsumer<>(props);
      // Subscribe to the topic.
      consumer.subscribe(Collections.singletonList(TOPIC));
      return consumer;
  }
    /*
    @Override
    public void run() {
        final Consumer<Long, String> consumer = createConsumer();
        final int giveUp = 100;   int noRecordsCount = 0;
        KafkaFlightStateProducer producer = new KafkaFlightStateProducer("STATS_RESP", Boolean.TRUE);
        


        while (true) {
            
            final ConsumerRecords<Integer, String> consumerRecords =
                    consumer.poll(1000);
                        System.out.println("Reached here");

            if (consumerRecords.count()==0) {
                noRecordsCount++;
                if (noRecordsCount > giveUp) break;
                else continue;
            }
            
            System.out.println("Reached here");
            consumerRecords.forEach(record -> {
                System.out.printf("Consumer Record:(%d, %s, %d, %d)\n",
                    record.key(), record.value(),
                    record.partition(), record.offset());
                if (record.value()!=null) {
                    producer.sendStats(record.value());
                }
            });
            consumer.commitAsync();
        }
        consumer.close();
        System.out.println("DONE");
    }
    */
    static void runConsumer() throws InterruptedException {
        final Consumer<Long, String> consumer = createConsumer();
        final int giveUp = 100;   int noRecordsCount = 0;
        KafkaFlightStateProducer producer = new KafkaFlightStateProducer("STATS_RESP", Boolean.TRUE);
        System.out.println("here");

        while (true) {
            final ConsumerRecords<Long, String> consumerRecords =
                    consumer.poll(1000);
            if (consumerRecords.count()==0) {
                noRecordsCount++;
                if (noRecordsCount > giveUp) break;
                else continue;
            }
            consumerRecords.forEach(record -> {
                System.out.printf("Consumer Record:(%d, %s, %d, %d)\n",
                        record.key(), record.value(),
                        record.partition(), record.offset());
                producer.sendStats(record.value());

            });
            
            
            consumer.commitAsync();
        }
        consumer.close();
        System.out.println("DONE");
    }
    
    
    
}

/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package https.airtracking.gitlab.io.airtracking;


import https.airtracking.gitlab.io.airtracking.Models.FlightStateMessage;
import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.common.serialization.LongDeserializer;
import org.apache.kafka.common.serialization.StringDeserializer;


import java.util.Collections;
import java.util.Properties;
/**
 *
 * @author jarturcosta
 */
public class KafkaFlightStateConsumer extends Thread {
    private final static String TOPIC = "ESP52-test2";
    private final static String BOOTSTRAP_SERVERS =
            "localhost:9092";

    public KafkaFlightStateConsumer() {
    }
    
    
    
    private static Consumer<Long, String> createConsumer() {
        final Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,
                BOOTSTRAP_SERVERS);
        props.put(ConsumerConfig.GROUP_ID_CONFIG,
                "KafkaExampleConsumer");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
                LongDeserializer.class.getName());
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
                StringDeserializer.class.getName());
        // Create the consumer using props.
        final Consumer<Long, String> consumer =
                new KafkaConsumer<>(props);
        // Subscribe to the topic.
        consumer.subscribe(Collections.singletonList(TOPIC));
        return consumer;
    }
    
    @Override
    public void run() {
        final Consumer<Long, String> consumer = createConsumer();
        final int giveUp = 100;   int noRecordsCount = 0;
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
                if (record.value()!=null) {
                    FlightStateMessage fsm = FlightStateDeserializer.deserialize(record.value());
                    System.out.println(fsm.toString());
                }
            });
            consumer.commitAsync();
        }
        consumer.close();
        System.out.println("DONE");
    }
    
    
    
}

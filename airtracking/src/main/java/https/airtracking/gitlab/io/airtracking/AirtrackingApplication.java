package https.airtracking.gitlab.io.airtracking;

import https.airtracking.gitlab.io.airtracking.kafka.KafkaStatsConsumer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackageClasses = FlightStateMessageController.class)
public class AirtrackingApplication implements CommandLineRunner{
    
    
    public static void main(String[] args) {
        
        
        SpringApplication.run(AirtrackingApplication.class, args);
        
        
    }
    
    @Override
    public void run(String[] args) throws Exception {
        System.out.println("Hey");
        
        KafkaStatsConsumer statsConsumer = new KafkaStatsConsumer();
        statsConsumer.run();
        
        //String allStates = KafkaFlightStateProducer.getAllStates();
        
        //KafkaFlightStateConsumer c = new KafkaFlightStateConsumer();
        //c.run();
        
        //KafkaFlightStateProducer producer = new KafkaFlightStateProducer("ESP52-test2", Boolean.FALSE);
        //producer.run();

    }
    
}

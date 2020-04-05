package https.airtracking.gitlab.io.airtracking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AirtrackingApplication implements CommandLineRunner{
    
    @Autowired
    private FlightStateRepository repository;
    
    public static void main(String[] args) {
        SpringApplication.run(AirtrackingApplication.class, args);
        
        
    }
    
    @Override
    public void run(String[] args) throws Exception {
        System.out.println("Hey");
        KafkaFlightStateConsumer c = new KafkaFlightStateConsumer();
        c.runConsumer();
    }
    
}

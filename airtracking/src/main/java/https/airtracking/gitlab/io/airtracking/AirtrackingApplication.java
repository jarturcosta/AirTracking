package https.airtracking.gitlab.io.airtracking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;

@SpringBootApplication
@ComponentScan(basePackageClasses = FlightStateMessageController.class)
public class AirtrackingApplication implements CommandLineRunner {

    @Autowired
    private Environment env;

    public static void main(String[] args) {

        SpringApplication.run(AirtrackingApplication.class, args);
    }

    @Override
    public void run(String[] args) throws Exception {
        System.out.println("Hey");

        //String allStates = KafkaFlightStateProducer.getAllStates();
        //KafkaFlightStateConsumer c = new KafkaFlightStateConsumer();
        //c.run();
        KafkaFlightStateProducer producer = new KafkaFlightStateProducer(env.getProperty("kafka.server"), "ESP52-test2", Boolean.FALSE);
        //producer.run();
    }
}

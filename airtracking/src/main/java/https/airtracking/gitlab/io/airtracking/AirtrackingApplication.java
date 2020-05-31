package https.airtracking.gitlab.io.airtracking;

import https.airtracking.gitlab.io.airtracking.kafka.KafkaStatsConsumer;
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

    }
}

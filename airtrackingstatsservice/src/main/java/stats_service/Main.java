package stats_service;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        KafkaFlightStateConsumer consumer = new KafkaFlightStateConsumer();
        consumer.runConsumer();

    }

}

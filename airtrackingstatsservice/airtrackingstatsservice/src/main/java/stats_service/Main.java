package stats_service;

import models.FlightStats;




public class Main{
    
    
    public static void main(String[] args) throws InterruptedException {
        
        KafkaFlightStateConsumer consumer = new KafkaFlightStateConsumer();
        consumer.runConsumer();
        
    }
    
    
}

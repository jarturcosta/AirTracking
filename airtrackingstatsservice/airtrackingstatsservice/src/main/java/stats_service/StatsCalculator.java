/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stats_service;

import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.*;
import com.mongodb.util.JSON;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import models.FlightState;
import models.FlightStateMessage;
import models.FlightStats;
import models.MongoFSM;
import org.bson.Document;

/**
 *
 * @author jarturcosta
 */
public class StatsCalculator {

    public StatsCalculator() {
    }
    
    
    
    public FlightStats getStatsByFlight(String icao24) {
        
        FlightStats result = new FlightStats();
        
        result.icao24 = icao24;
        
        result.max_speed = 0;
        
        float speedSum = 0;
        
        float vrateSum = 0;
        
        int cnt = 0;
        
        List<Float> v_rates = new ArrayList<>();
        
        System.out.println("Here 1");

        MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
        System.out.println("Here 2");
        MongoDatabase db = mongoClient.getDatabase("test" );
        MongoCollection<org.bson.Document> collection = db.getCollection("flight_state_messages");
        
        System.out.println("Here 3");
        Gson gson = new Gson();
        
        try (MongoCursor<org.bson.Document> cur = collection.find().iterator()) {

                while (cur.hasNext()) {

                    Document doc = cur.next();
                    /*
                    ArrayList<Object> fsm = new ArrayList<>(doc.values());
                    for(Object f : fsm) {
                        //FlightStateMessage flight = gson.fromJson(f, FlightStateMessage.class);
                        System.out.println(f.);

                    }*/
                    
                    String jsonStr = JSON.serialize(doc);
                    
                    MongoFSM fsm = gson.fromJson(jsonStr, MongoFSM.class);
                    System.out.println("FSM:" + fsm.toString());
                    for (FlightState flightState : fsm.getStates()) {
                        if (flightState.getIcao24().equals(icao24)) {
                            System.out.println("HAAAAAAAAAAAAAAAAAAAAAAAAAAA");
                            float speed = Float.parseFloat(flightState.getVelocity());
                            if (speed > result.max_speed) {
                                result.max_speed = speed;
                            }
                            speedSum += speed;
                            vrateSum += (Float.parseFloat(flightState.getVertical_rate()));
                            cnt++;
                        }
                    }
                    
                    System.out.println("Speed: " + speedSum + " / " + cnt);
                    result.avg_speed = speedSum/(float) cnt;
                    result.avg_vertical_rate = vrateSum/(float) cnt;


                    //System.out.printf("%s: %s%n", fsm.get(1), fsm.get(2));
                }
            }
        
        
        return result;
    }
    
}
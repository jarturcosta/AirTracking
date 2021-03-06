/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package https.airtracking.gitlab.io.airtracking.Models;
import java.io.Serializable;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.lang.Nullable;

/**
 *
 * @author jarturcosta
 */
 @Document(collection="flight_states")
public class FlightState implements Serializable{
    
    @Id
    private String icao24;
    private String origin_contry;
    private String time_position, last_contact;
    private String longitude;
    private String latitude;
    private String velocity, vertical_rate;
    private String on_ground;

    public FlightState(String icao24, String origin_contry, String time_position, String last_contact, String longitude, String latitude, String velocity, String vertical_rate, String on_ground) {
        this.icao24 = icao24;
        this.origin_contry = origin_contry;
        this.time_position = time_position; 
        this.last_contact = last_contact;
        this.longitude = longitude;
        this.latitude = latitude;
        this.velocity = velocity;
        this.vertical_rate = vertical_rate;
        this.on_ground = on_ground;
    }

    @Id
    public String getIcao24() {
        return icao24;
    }

    public void setIcao24(String icao24) {
        this.icao24 = icao24;
    }

    public String getOrigin_contry() {
        return origin_contry;
    }

    public void setOrigin_contry(String origin_contry) {
        this.origin_contry = origin_contry;
    }

    public String getTime_position() {
        return time_position;
    }

    public void setTime_position(String time_position) {
        this.time_position = time_position;
    }

    public String getLast_contact() {
        return last_contact;
    }

    public void setLast_contact(String last_contact) {
        this.last_contact = last_contact;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getVelocity() {
        return velocity;
    }

    public void setVelocity(String velocity) {
        this.velocity = velocity;
    }

    public String getVertical_rate() {
        return vertical_rate;
    }

    public void setVertical_rate(String vertical_rate) {
        this.vertical_rate = vertical_rate;
    }

    public String isOn_ground() {
        return on_ground;
    }

    public void setOn_ground(String on_ground) {
        this.on_ground = on_ground;
    }

    @Override
    public String toString() {
        return "{icao24=" + icao24 + ", origin_contry=" + origin_contry + ", time_position=" + time_position + ", last_contact=" + last_contact + ", longitude=" + longitude + ", latitude=" + latitude + ", velocity=" + velocity + ", vertical_rate=" + vertical_rate + ", on_ground=" + on_ground + '}';
    }
    
    
    
    
}

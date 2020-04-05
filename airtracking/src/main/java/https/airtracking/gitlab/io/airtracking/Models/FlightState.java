/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package https.airtracking.gitlab.io.airtracking.Models;
import java.io.Serializable;
import org.springframework.data.annotation.Id;

/**
 *
 * @author jarturcosta
 */
public class FlightState implements Serializable{
    
    @Id
    private String icao24;
    private String origin_contry;
    private int time_position, last_contact;
    private double longitude, latitude, velocity, vertical_rate;
    private boolean on_ground;

    public FlightState(String icao24, String origin_contry, int time_position, int last_contact, double longitude, double latitude, double velocity, double vertical_rate, boolean on_ground) {
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

    public int getTime_position() {
        return time_position;
    }

    public void setTime_position(int time_position) {
        this.time_position = time_position;
    }

    public int getLast_contact() {
        return last_contact;
    }

    public void setLast_contact(int last_contact) {
        this.last_contact = last_contact;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getVelocity() {
        return velocity;
    }

    public void setVelocity(double velocity) {
        this.velocity = velocity;
    }

    public double getVertical_rate() {
        return vertical_rate;
    }

    public void setVertical_rate(double vertical_rate) {
        this.vertical_rate = vertical_rate;
    }

    public boolean isOn_ground() {
        return on_ground;
    }

    public void setOn_ground(boolean on_ground) {
        this.on_ground = on_ground;
    }

    @Override
    public String toString() {
        return "{icao24=" + icao24 + ", origin_contry=" + origin_contry + ", time_position=" + time_position + ", last_contact=" + last_contact + ", longitude=" + longitude + ", latitude=" + latitude + ", velocity=" + velocity + ", vertical_rate=" + vertical_rate + ", on_ground=" + on_ground + '}';
    }
    
    
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package https.airtracking.gitlab.io.airtracking.Models;

/**
 *
 * @author jarturcosta
 */
public class FlightStats {
    
    public String icao24;
    
    public float max_speed;
    
    public float avg_speed;
    
    public float avg_vertical_rate;   

    public String getIcao24() {
        return icao24;
    }

    public float getMax_speed() {
        return max_speed;
    }

    public float getAvg_speed() {
        return avg_speed;
    }

    public float getAvg_vertical_rate() {
        return avg_vertical_rate;
    }

    public void setIcao24(String icao24) {
        this.icao24 = icao24;
    }

    public void setMax_speed(float max_speed) {
        this.max_speed = max_speed;
    }

    public void setAvg_speed(float avg_speed) {
        this.avg_speed = avg_speed;
    }

    public void setAvg_vertical_rate(float avg_vertical_rate) {
        this.avg_vertical_rate = avg_vertical_rate;
    }
    
}

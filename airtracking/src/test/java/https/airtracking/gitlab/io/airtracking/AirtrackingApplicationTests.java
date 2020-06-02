package https.airtracking.gitlab.io.airtracking;

import com.google.gson.Gson;
import https.airtracking.gitlab.io.airtracking.Models.FlightState;
import https.airtracking.gitlab.io.airtracking.Models.FlightStateMessage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import static org.junit.Assert.assertTrue;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;


//@SpringBootTest
public class AirtrackingApplicationTests{
    
        private static FlightStateMessage toSave;
        private static FlightState toCheck;
        private static HttpURLConnection con;
        private static URL url;

        
        public AirtrackingApplicationTests() {
            
        }
    
        @BeforeAll
        public static void initialize() throws MalformedURLException, IOException {
            
            
            toCheck = new FlightState("ab172vs", "Portugal", "15273617", "15273617" , "47.123141", "-3.127371", "203.2", "-0.23", "false");
            List<FlightState> states = new ArrayList<>();
            states.add(toCheck);
            toSave = new FlightStateMessage(123456, states);
            url = new URL("http://192.168.160.103:9069/flightstates/byTime/" + Integer.toString(123456));
            con = (HttpURLConnection) url.openConnection();

        }

	@Test
	public void testInsertion() throws Exception {
            
            
            sendPost(toSave.toString());
            Thread.sleep(5000);
                    
            
            Gson gson = new Gson();
            FlightStateMessage fsm = gson.fromJson(getState(123456), FlightStateMessage.class);

            assertTrue(fsm.getStates().get(0).equals(toCheck));
            
            
	}
        
        @AfterAll
        public static void remove() throws IOException {
            System.out.println("Here");
            deleteState(123456);

        }
       
        
        private void sendPost(String body) throws Exception {

            HttpPost post = new HttpPost("http://192.168.160.103:9069/flightstates");

            StringEntity entity = new StringEntity(body);
            post.setHeader("Accept", "application/json");
            post.setHeader("Content-type", "application/json");
            post.setEntity(entity);

            try (CloseableHttpClient httpClient = HttpClients.createDefault();
                 CloseableHttpResponse response = httpClient.execute(post)) {

                System.out.println(EntityUtils.toString(response.getEntity()));
            }

        }
    
    public static String getState(int time) throws MalformedURLException, IOException {
        con.setRequestMethod("GET");
        
        int status = con.getResponseCode();
        
        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuilder content = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();
        
        return content.toString();
    }
    
    public static void deleteState(int time) throws MalformedURLException, IOException {
        con = (HttpURLConnection) url.openConnection();
        con.setDoOutput(true);
        con.setRequestProperty(
            "Content-Type", "application/x-www-form-urlencoded" );
        con.setRequestMethod("DELETE");
        con.connect();
    }

}

package edu.eci.arep.parcial.proxy;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

@RestController
public class ProxyController {
    List<String>Urls= Arrays.asList("http://localhost:8080", "http://localhost:8081");
    int currentIndex=0;

    @GetMapping("proxy/factors")
    public ResponseEntity<String>factors(@RequestParam("value")int value) throws IOException {
        String serviceUrl = getNextUrl();
        String url = serviceUrl + "/factors?value=" + value;
        return sendRequest(url);
    }

    @GetMapping("proxy/primes")
    public ResponseEntity<String>primes(@RequestParam("value")int value) throws IOException {
        String serviceUrl = getNextUrl();
        String url = serviceUrl + "/primes?value=" + value;
        return sendRequest(url);
    }

    private String getNextUrl(){
        String url = Urls.get(currentIndex);
        currentIndex = (currentIndex+1)%2;
        return url;
    }

    private ResponseEntity<String>sendRequest(String url) throws IOException {
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        int responseCode = con.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) { // success
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            return ResponseEntity.ok(response.toString());
        } else {
            return ResponseEntity.badRequest().body("GET request not worked");
        }
    }
}

package edu.eci.arep.parcial.MathService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class PrimesController {
    @GetMapping("/primes")
    public ResponseEntity<Map<String, Object>>primes(@RequestParam("value")int value){

        List<Integer>list=getPrimes(value);
        Map<String, Object>response=new HashMap<>();
        response.put("operation", "primes");
        response.put("input", value);
        response.put("output", list);
        return ResponseEntity.ok(response);
    }

    private List<Integer>getPrimes(int value){
        List<Integer>primes=new ArrayList<>();
        for(int i=2; i<=value; i++){
            if(isPrime(i)){
                primes.add(i);
            }
        }
        return primes;
    }

    private boolean isPrime(int num){
        for(int i=2; i<num; i++){
            if(num==2){
                return true;
            }else if(num%2==0){
                return false;
            }
        }
        return true;
    }
}

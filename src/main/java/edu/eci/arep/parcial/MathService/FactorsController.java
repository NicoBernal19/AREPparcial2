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
public class FactorsController {
    @GetMapping("/factors")
    public ResponseEntity<Map<String, Object>>factors(@RequestParam("value")int value){

        List<Integer>list=getFactors(value);
        Map<String, Object>response = new HashMap<>();
        response.put("operation", "factors");
        response.put("input", value);
        response.put("output", list);
        return ResponseEntity.ok(response);
    }

    private List<Integer> getFactors(int num){
        List<Integer> factors=new ArrayList<>();
        for(int i=1; i<= num; i++){
            if(num%i==0){
                factors.add(i);
            }
        }
        return factors;
    }
}

package com.example.demo;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/coins")
@CrossOrigin(origins = "http://localhost:3000") // Add this annotation for CORS
public class CoinCalculatorController {

    @PostMapping("/minimum")
    public ResponseEntity<?> getMinimumCoins(@RequestBody CoinRequest request) {
        try {
            System.out.println("Request received: " + request);
            List<Double> result = CoinCalculator.getMinimumCoins(request.getTargetAmount(), request.getDenominations());
            System.out.println("Result calculated: " + result);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            System.err.println("Error processing request: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Invalid request. " + e.getMessage());
        }
    }
}

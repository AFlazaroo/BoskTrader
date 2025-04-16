package com.edu.unbosque.controller;

import com.edu.unbosque.service.AlpacaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/alpaca")
public class AlpacaController {

    private final AlpacaService alpacaService;

    public AlpacaController(AlpacaService alpacaService) {
        this.alpacaService = alpacaService;
    }

    // Endpoint para obtener el balance de la cuenta
    @GetMapping("/balance")
    public String getBalance() {
        return alpacaService.getAccountBalance();
    }

    // Endpoint para obtener la cotización de una acción
    @GetMapping("/quote/{symbol}")
    public Map<String, Object> getQuote(@PathVariable String symbol) {
        return alpacaService.getStockQuote(symbol);
    }

    // Endpoint para obtener datos de velas japonesas (historical candles)
    @GetMapping("/historical/{symbol}/{timeFrame}")
    public ResponseEntity<?> getHistoricalData(
            @PathVariable String symbol,
            @PathVariable String timeFrame,
            @RequestParam String start,
            @RequestParam String end) {
        try {
            List<Map<String, Object>> candles = alpacaService.getHistoricalCandles(symbol, timeFrame, start, end);
            return ResponseEntity.ok(candles);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }


    // Endpoint para realizar una orden de compra
    @PostMapping("/buy")
    public String placeBuyOrder(@RequestParam String symbol, @RequestParam int qty, @RequestParam double price) {
        return alpacaService.placeBuyOrder(symbol, qty, price);
    }

    // Endpoint para obtener las posiciones abiertas (portafolio)
    @GetMapping("/positions")
    public String getOpenPositions() {
        return alpacaService.getOpenPositions();
    }
}



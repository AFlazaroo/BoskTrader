package com.edu.unbosque.controller;

import com.edu.unbosque.model.Orden;
import com.edu.unbosque.model.Usuario;
import com.edu.unbosque.service.AlpacaService;
import com.edu.unbosque.service.OrdenService;
import com.edu.unbosque.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/orders")
public class OrdenController {

    private final OrdenService ordenService;
    private final AlpacaService alpacaService;

    @Autowired
    public OrdenController(OrdenService ordenService, AlpacaService alpacaService) {
        this.ordenService = ordenService;
        this.alpacaService = alpacaService;
    }

    @PostMapping("/market")
    public ResponseEntity<String> placeMarketOrder(
            @RequestParam String symbol,
            @RequestParam int qty,
            @RequestParam String side
    ) {
        try {
            String result = ordenService.placeMarketOrder(symbol, qty, side);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al ejecutar orden de mercado: " + e.getMessage());
        }
    }

    @PostMapping("/market/sell")
    public ResponseEntity<String> placeMarketOrder(
            @RequestParam String symbol,
            @RequestParam int qty,
            @RequestParam String side,
            @RequestParam String time
    ) {
        try {
            String result = ordenService.placeMarketOrder(symbol, qty, side,time);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al ejecutar orden de mercado: " + e.getMessage());
        }
    }

    @PostMapping("/limit")
    public ResponseEntity<String> placeLimitOrder(
            @RequestParam String symbol,
            @RequestParam int qty,
            @RequestParam double price
    ) {
        try {
            String result = ordenService.placeLimitOrder(symbol, qty, price);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al ejecutar orden límite: " + e.getMessage());
        }
    }

    @PostMapping("/stoploss")
    public ResponseEntity<String> placeStopLossOrder(
            @RequestParam String symbol,
            @RequestParam int qty,
            @RequestParam String side,
            @RequestParam double stopPrice
    ) {
        try {
            String result = ordenService.placeStopLossOrder(symbol, qty, side, stopPrice);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al ejecutar orden Stop Loss: " + e.getMessage());
        }
    }

    @PostMapping("/takeprofit")
    public ResponseEntity<String> placeTakeProfitOrder(
            @RequestParam String symbol,
            @RequestParam int qty,
            @RequestParam String side,
            @RequestParam double limitPrice
    ) {
        try {
            String result = ordenService.placeTakeProfitOrder(symbol, qty, side, limitPrice);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al ejecutar orden Take Profit: " + e.getMessage());
        }
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<String> getOrderById(@PathVariable String orderId) {
        try {
            String order = ordenService.getOrderById(orderId);
            return ResponseEntity.ok(order);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al obtener la orden: " + e.getMessage());
        }
    }
}

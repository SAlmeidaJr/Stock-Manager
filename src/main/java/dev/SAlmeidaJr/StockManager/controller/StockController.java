package dev.SAlmeidaJr.StockManager.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
class StockController {

    @GetMapping("/hello")
    String getHelloWorld(){
        return "Hello, World!";
    }
}

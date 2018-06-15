package pl.inf.erp.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/v1/api")
public class MainController {

    @GetMapping(value = "/hello")
    public ResponseEntity getHello(){
        return ResponseEntity.ok("hello");
    }
}

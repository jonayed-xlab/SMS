package com.jontech.smsserver.controller;

import com.jontech.smsserver.model.Sms;
import com.jontech.smsserver.service.SmsSenderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/api/v1")
public class SmsSenderController {

    SmsSenderService smsSenderService;

    public SmsSenderController(SmsSenderService smsSenderService) {
        this.smsSenderService = smsSenderService;
    }

    @PostMapping("/send")
    public ResponseEntity<?> sendMessage(@RequestBody Sms sms) {
        Map<String, Sms> response = new HashMap<>();
        Sms smsResponse = smsSenderService.send(sms);
        response.put("payload", smsResponse);
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }
}

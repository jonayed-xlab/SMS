package com.jontech.smsserver.controller;

import com.jontech.smsserver.model.Sms;
import com.jontech.smsserver.service.SmsReceivedService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/api/v1")
public class SmsRecivedController {

    SmsReceivedService smsReceivedService;

    public SmsRecivedController(SmsReceivedService smsReceivedService) {
        this.smsReceivedService = smsReceivedService;
    }

    @PostMapping("/send")
    public ResponseEntity<?> sendMessage(@RequestBody Sms sms) {
        Map<String, Sms> response = new HashMap<>();
        Sms smsResponse = smsReceivedService.send(sms);
        response.put("payload", smsResponse);
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }
}

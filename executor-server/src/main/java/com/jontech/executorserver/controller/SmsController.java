package com.jontech.executorserver.controller;

import com.jontech.executorserver.model.Sms;
import com.jontech.executorserver.service.SmsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/v1")
@CrossOrigin(origins = "*")
public class SmsController {

    private SmsService smsService;

    public SmsController(SmsService smsService) {
        this.smsService = smsService;
    }

    @PostMapping(value = "/save-sms")
    public ResponseEntity<?> save(@RequestBody Sms sms) {
        Map<String, Sms> response = new HashMap<>();

        Sms smsResponse = smsService.save(sms);
        response.put("payload", smsResponse);

        return new ResponseEntity<>(response, HttpStatus.CREATED);

    }

    @GetMapping(value = "/{idSmsKey}")
    public ResponseEntity<?> getSms(@PathVariable long idSmsKey) {
        Map<String, Sms> response = new HashMap<>();

        Sms smsResponse = smsService.getSms(idSmsKey);
        response.put("payload", smsResponse);

        if (smsResponse == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = "/get-sms")
    public ResponseEntity<?> getAllSms() {
        Map<String, List<Sms>> response = new HashMap<>();

        List<Sms> smsResponse = smsService.getAllSms();
        response.put("payload", smsResponse);

        if (smsResponse == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{idSmsKey}")
    public ResponseEntity<?> updateSms(@PathVariable Long idSmsKey, @RequestBody Sms sms) {
        Map<String, Sms> response = new HashMap<>();

        Sms smsResponse = smsService.updateSms(idSmsKey, sms);
        response.put("payload", smsResponse);

        if (smsResponse == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}

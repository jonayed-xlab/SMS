package com.jontech.executorserver.service;

import com.jontech.executorserver.model.Sms;
import com.jontech.executorserver.repository.SmsRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SmsService {

    private SmsRepository smsRepository;

    public SmsService(SmsRepository smsRepository) {
        this.smsRepository = smsRepository;
    }

    public Sms save(Sms sms) {
        if (sms == null)
            throw new NullPointerException("Sms object is null");
        Sms smsResponse = smsRepository.save(sms);
        return smsResponse;
    }

    public Sms getSms(Long idSmsKey) {
        Sms smsResponse = smsRepository.findByIdSmsKey(idSmsKey);
        return smsResponse;
    }

    public List<Sms> getAllSms() {
        List<Sms> smsList = smsRepository.findAll();
        return smsList;
    }

    public Sms updateSms(Long idSmsKey, Sms sms) {
        Optional<Sms> smsOptional = smsRepository.findById(idSmsKey);

        if (!smsOptional.isPresent()) {
            throw new IllegalArgumentException("Sms with id " + idSmsKey + " not found");
        }

        Sms updatedSms = smsOptional.get();
        updatedSms.setIdSmsVersion(smsOptional.get().getIdSmsVersion() + 1);
        updatedSms.setModifiedDate(sms.getModifiedDate());
        updatedSms.setModifiedUser(sms.getModifiedUser());
        updatedSms.setMessage(sms.getMessage());
        updatedSms.setStatus(sms.getStatus());
        updatedSms.setMessageType(sms.getMessageType());
        Sms smsResponse = smsRepository.save(updatedSms);

        return smsResponse;
    }
}


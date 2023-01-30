package com.jontech.executorserver.repository;

import com.jontech.executorserver.model.Sms;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SmsRepository extends JpaRepository<Sms, Long> {

    Sms findByIdSmsKey(long idSmsKey);

}

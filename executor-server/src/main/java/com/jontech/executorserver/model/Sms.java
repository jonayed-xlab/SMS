package com.jontech.executorserver.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

@Entity(name = "T_SMS_HISTORY")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Sms {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_sms_key")
    private Long idSmsKey;

    @Column(name = "id_sms_ver")
    private Integer idSmsVersion;

    @Column(name = "is_active")
    private Integer isActive;

    @Column(name = "user_created_key")
    private Integer createdUser;

    @Column(name = "user_mod_key")
    private Integer modifiedUser;

    @Column(name = "dtt_created")
    private String createdDate;

    @Column(name = "dtt_mod")
    private String modifiedDate;

    @Column(name = "tx_message")
    private String message;

    @Column(name = "tx_message_type")
    private String messageType;

    @Column(name = "tx_status")
    private String status;

}

package com.test.customelockdemo.model;

import com.test.customelockdemo.CustomLockNameEnum;

import javax.persistence.*;
import java.util.Date;

public class CustomLockEntity {
    public static final String ENTITY_NAME = "entity_name";
    public static final String ENTITY_ID = "entity_id";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "entity_name")
    private CustomLockNameEnum entityName;

    @Column(name = "entity_id")
    private String entityId;

    @Column(name = "created_at", insertable = false, updatable = false,
            columnDefinition = "TIMESTAMP not null default CURRENT_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
}

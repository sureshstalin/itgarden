package com.itgarden.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Data
@MappedSuperclass
public class BaseObject implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "REC_ID")
    private Long recId;

    @JsonIgnore
    @Column(name = "IS_DELETED")
    private boolean deleted;

    @JsonIgnore
    @Column(name = "DATE_CREATED")
    private Timestamp dateCreated = new Timestamp(System.currentTimeMillis());

    @JsonIgnore
    @Column(name = "DATE_MODIFIED")
    private Timestamp dateModified;
}

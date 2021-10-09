package com.example.backend.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.print.attribute.standard.DateTimeAtCompleted;
import java.util.Date;

@Entity
@Getter
@Setter


public class ChatJoinRoom {

    @CreationTimestamp
    private Date registerDate;

    @Column(nullable = false,columnDefinition = "varchar(5)")
    private char status;




}

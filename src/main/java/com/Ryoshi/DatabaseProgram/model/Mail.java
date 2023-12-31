package com.Ryoshi.DatabaseProgram.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Mail {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @ManyToOne
    private User author;
    @ManyToOne
    private User recipient;
    private String title;
    private String content;
    private Timestamp received;
    private Boolean viewed;

}

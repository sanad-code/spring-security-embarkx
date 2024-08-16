package com.sanadcode.springsecurityembarkx.note;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    // @Lob is used to store large text data
    @Lob
    private String content;
    private String ownerUsername;
}

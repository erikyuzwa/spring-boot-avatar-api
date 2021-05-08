package com.wazooinc.avatarapi.models;

import java.io.Serializable;
import java.time.Instant;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity
public class Avatar implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    private String name;

    // the "class" of our Avatar
    @Getter
    @Setter
    private String type;

    @Getter
    @Setter
    private int healthPoints;

    @Getter
    @Setter
    private int manaPoints;

    @Getter
    @Setter
    private Instant dateCreated = Instant.now();

    @Getter
    @Setter
    private Instant dateModified = Instant.now();

    public Avatar() {
        
    }

    @Override
    public String toString() {
        return "Avatar{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", type='" + type + '\'' +
            ", healthPoints=" + healthPoints +
            ", manaPoints=" + manaPoints +
            ", dateCreated='" + dateCreated + '\'' +
            ", dateModified='" + dateModified + '\'' +
            "}";
    }
    
}
package com.wazooinc.avatarapi.models;

import java.io.Serializable;
import java.time.Instant;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "avatars")
public class Avatar implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    @Enumerated(EnumType.STRING)
    private ClassType classType;

    @Getter
    @Setter
    @Enumerated(EnumType.STRING)
    private RaceType raceType;

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

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @Setter
    private User user;

    public Avatar() {
        
    }

    @Override
    public String toString() {
        return "Avatar{" +
            "id=" + id +
            "userId=" + user.getId() +
            ", name='" + name + '\'' +
            ", classType='" + classType + '\'' +
            ", raceType='" + raceType + '\'' +
            ", healthPoints=" + healthPoints +
            ", manaPoints=" + manaPoints +
            ", dateCreated='" + dateCreated + '\'' +
            ", dateModified='" + dateModified + '\'' +
            "}";
    }
    
}

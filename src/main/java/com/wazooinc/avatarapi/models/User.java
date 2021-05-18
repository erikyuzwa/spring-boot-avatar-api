package com.wazooinc.avatarapi.models;

import java.io.Serializable;
import java.time.Instant;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "users")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    private String username;

    @Getter
    @Setter
    private String password;

    @Getter
    @Setter
    private Instant dateCreated = Instant.now();

    @Getter
    @Setter
    private Instant dateModified = Instant.now();

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @Getter
    @Setter
    private Set<Avatar> avatars;

    @Override
    public String toString() {
        return "User{" +
            "id=" + id +
            ", username='" + username + '\'' +
            ", passwordHash='" + password + '\'' +
            ", dateCreated='" + dateCreated + '\'' +
            ", dateModified='" + dateModified + '\'' +
            "}";
    }
    
}

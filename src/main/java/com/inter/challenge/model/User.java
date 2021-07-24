package com.inter.challenge.model;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by Regis Gaboardi (@gmail.com)
 * Provided with Love and IntelliJ IDEA for challenge.
 * 13/07/2021
 */

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(length = 512)
    private String name;
    @Column(length = 512)
    private String email;
    @ManyToMany(targetEntity = VerifierDigit.class, cascade = CascadeType.ALL,
            fetch = FetchType.EAGER)
    private Set<VerifierDigit> verifiers;

    public User(String name, String email, Set<VerifierDigit> verifiers) {
        this.name = name;
        this.email = email;
        this.verifiers = verifiers;
    }

    public User() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<VerifierDigit> getVerifiers() {
        return verifiers;
    }

    public void setVerifiers(Set<VerifierDigit> verifiers) {
        this.verifiers = verifiers;
    }

}

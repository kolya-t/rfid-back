package ru.kolya.rfidserver.model;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "users")
@Getter
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false, unique = true, length = 10)
    private String cardNumber;

    @Column(nullable = false)
    private Long rubInHour;

    @Column(nullable = false)
    private Boolean isAdmin;

    public User(String firstName, String lastName, String cardNumber, Long rubInHour, Boolean isAdmin) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.cardNumber = cardNumber;
        this.rubInHour = rubInHour;
        this.isAdmin = isAdmin;
    }
}

package ru.kolya.rfidserver.config.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import ru.kolya.rfidserver.model.User;

import java.util.Collections;

public class Details extends org.springframework.security.core.userdetails.User {
    public static final String ADMIN = "admin";
    public static final String USER = "user";

    private static GrantedAuthority getAuthority(User user) {
        return new SimpleGrantedAuthority(user.getIsAdmin() ? ADMIN : USER);
    }

    public Details(User user) {
        super(user.getCardNumber(), user.getCardNumber(), Collections.singleton(getAuthority(user)));
    }
}

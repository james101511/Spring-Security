package com.workshop.james.repo;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

@Repository
public class UserDetailsRepository {

  private List<UserDetails> APPLICATION_USERS() {
    return Arrays.asList(
        new User(
            "james@yahoo.com.tw",
            "password",
            Collections.singleton(new SimpleGrantedAuthority("ROLE_ADMIN"))
        ),
        new User(
            "james@gmail.com",
            "password",
            Collections.singleton(new SimpleGrantedAuthority("USER"))
        ));
  }


  public UserDetails findUserDetailByEmail(String email) {
    return APPLICATION_USERS().stream()
        .filter(u -> u.getUsername().equals(email))
        .findFirst()
        .orElseThrow(() -> new UsernameNotFoundException("No user found"));
  }

}

package com.blogposttodo.blogposttodo.jwt.services;

import com.blogposttodo.blogposttodo.jwt.models.UserPrincipal;
import com.blogposttodo.blogposttodo.userlogin.entity.UserLoginEntity;
import com.blogposttodo.blogposttodo.userlogin.service.UserLoginService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


@Service
@AllArgsConstructor
public class ApplicationUserDetailsService  implements UserDetailsService {

    private final UserLoginService userLoginService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return new UserPrincipal(userLoginService.searchByEmail(email));
    }

    public UserLoginEntity authenticate(String email, String password) throws NoSuchAlgorithmException {
        if (email.isEmpty() || password.isEmpty()) throw new BadCredentialsException("Unauthorized");

        var userLoginEntity = userLoginService.searchByEmail(email);

        if (userLoginEntity == null) throw new BadCredentialsException("Unauthorized");

        var verified = verifyPasswordHash(password, userLoginEntity.getStoredHash(), userLoginEntity.getStoredSalt());

        if(!verified) throw new BadCredentialsException("Unauthorized");

        return userLoginEntity;

    }

    private Boolean verifyPasswordHash(
            String password,
            byte[] storedHash,
            byte[] storedSalt) throws NoSuchAlgorithmException {
        if (password.isBlank() || password.isEmpty() ) throw new IllegalArgumentException("Password is empty");

        if(storedHash.length != 64) throw new IllegalArgumentException("Invalid length of password hash (64 bytes expected");

        if(storedSalt.length != 128) throw new IllegalArgumentException("Invalid length of password salt (128 bytes expected");

        var md = MessageDigest.getInstance("SHA-512");
        md.update(storedSalt);

        var computedHash = md.digest(password.getBytes(StandardCharsets.UTF_8));

        for(int i = 0; i < computedHash.length; i++) {
            if(computedHash[i] != storedHash[i]) return false;
        }

        //the above code is equivalent to:
        //return Arrays.equals(computedHash, storedHash);
        return MessageDigest.isEqual(computedHash, storedHash);
    }
}

package com.blogposttodo.blogposttodo.userlogin.service;

import com.blogposttodo.blogposttodo.exception.BadRequestException;
import com.blogposttodo.blogposttodo.exception.NotFoundException;
import com.blogposttodo.blogposttodo.userlogin.dto.UserLoginDto;
import com.blogposttodo.blogposttodo.userlogin.entity.UserLoginEntity;
import com.blogposttodo.blogposttodo.userlogin.repository.UserLoginRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class UserLoginService {

    private final UserLoginRepository userLoginRepository;
    private final ModelMapper modelMapper;

    public UserLoginEntity searchByEmail(String email) {
        return userLoginRepository.findByEmail(email);
    }

    public List<UserLoginDto> getAllUsersLogin() {
        List<UserLoginEntity> userLoginEntities = userLoginRepository.findAll();
          var userLoginEntityList = new ArrayList<>(userLoginRepository.findAll());

        return userLoginEntityList
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public UserLoginDto findUserLoginById(UUID id) {
        var user = userLoginRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User with that id" + id + "was not found"));

        return convertToDto(user);
    }


    public UserLoginDto createUserLogin(UserLoginDto userLoginDto, String password)  throws NoSuchAlgorithmException {
        var user = convertToEntity(userLoginDto);

        if (password.isBlank()) throw new IllegalArgumentException(
                "Password is required"
        );

        var existsEmail = userLoginRepository.selectExistsByEmail(user.getEmail());
        if (existsEmail) throw new BadRequestException(
                "Email " + user.getEmail() + " taken"
        );

        byte[] salt = createSalt();
        byte[] hashedPassword = createPasswordHash(password, salt);

        user.setStoredSalt(salt);
        user.setStoredHash(hashedPassword);

        userLoginRepository.save(user);

        return convertToDto(user);

    };

    public void updateUserLogin(UUID id, UserLoginDto userLoginDto, String password) throws NoSuchAlgorithmException {
        var user = findOrThrow(id);
        var userParam = convertToEntity(userLoginDto);

        user.setEmail(userParam.getEmail());
        user.setMobileNumber(userParam.getMobileNumber());

        if (!password.isBlank()) {
            byte[] salt = createSalt();
            byte[] hashedPassword = createPasswordHash(password, salt);

            user.setStoredSalt(salt);
            user.setStoredHash(hashedPassword);
        }

        userLoginRepository.save(user);
    }

    public void deleteUserLogin(UUID id) {
        findOrThrow(id);
        userLoginRepository.deleteById(id);
    }

    private byte[] createSalt() {
        var random = new SecureRandom();
        var salt = new byte[128];
        random.nextBytes(salt);
        return salt;
    }

    private byte[] createPasswordHash(String password, byte[] salt)
            throws NoSuchAlgorithmException {
        var md = MessageDigest.getInstance("SHA-512");
        md.update(salt);

        return md.digest(password.getBytes(StandardCharsets.UTF_8));
    }


    private UserLoginEntity findOrThrow(final UUID id) {
        return userLoginRepository.findById(id).orElseThrow(
                () -> new NotFoundException("User not found")
        );
    }

    private UserLoginDto convertToDto(UserLoginEntity entity) {
        return modelMapper.map(entity, UserLoginDto.class);
    }

    private UserLoginEntity convertToEntity(UserLoginDto dto) {
        return modelMapper.map(dto, UserLoginEntity.class);
    }
}

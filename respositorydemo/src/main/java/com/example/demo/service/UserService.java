package com.example.demo.service;

import com.example.demo.entity.UserEntity;
import com.example.demo.mongo.dto.UserDto;
import com.example.demo.mongo.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


    public List<UserDto> getUsers() {
        List<UserEntity> userEntities = userRepository.findAll();
        List<UserDto> userDtos = new ArrayList<>();
        for (UserEntity userEntity : userEntities) {
            UserDto userDto = new UserDto();
            BeanUtils.copyProperties(userEntity, userDto);
            userDtos.add(userDto);
        }
        return userDtos;
    }

    public UserDto createUser(UserDto userDto) {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(UUID.randomUUID().toString());
        userEntity.setCreationTime(ZonedDateTime.now(ZoneOffset.UTC).toString());
        userEntity.setFirstName(userDto.getFirstName());
        userEntity.setLastName(userDto.getLastName());
        UserEntity entity = userRepository.save(userEntity);
        UserDto response = new UserDto();
        BeanUtils.copyProperties(entity, response);
        return response;
    }

    public UserDto updateUser(UserDto userDto) {
        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(userDto, userEntity);
        userEntity.setUpdatedTime(ZonedDateTime.now(ZoneOffset.UTC).toString());
        UserEntity entity = userRepository.save(userEntity);
        UserDto response = new UserDto();
        BeanUtils.copyProperties(entity, response);
        return response;
    }

    public UserDto getUserById(String id) {
        UserDto userDto = new UserDto();
        Optional<UserEntity> userEntityOptional = userRepository.findById(id);
        if (userEntityOptional.isPresent()) {
            BeanUtils.copyProperties(
                    userEntityOptional.get(),userDto);
        }
        return userDto;
    }

    public UserDto deleteUserById(String id) {
        Optional<UserEntity> userDtoOptional = userRepository.findById(id);
        UserDto userDto = new UserDto();
        if (userDtoOptional.isPresent()) {
            BeanUtils.copyProperties(userDtoOptional.get(), userDto);
        }
        userRepository.deleteById(id);
        return userDto;
    }
}

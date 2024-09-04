package com.engapp.UserService.mapper;


import com.engapp.UserService.dto.request.UserRequest;
import com.engapp.UserService.dto.response.SecurityUserResponse;
import com.engapp.UserService.dto.response.UserResponse;
import com.engapp.UserService.pojo.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User userRequestToUser(UserRequest userRequest);

    UserResponse userToUserResponse(User user);

    User userReponseToUser(UserResponse userResponse);

    SecurityUserResponse userToSecurityUserResponse(User user);
}

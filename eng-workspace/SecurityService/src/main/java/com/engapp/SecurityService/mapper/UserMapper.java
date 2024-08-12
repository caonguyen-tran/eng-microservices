package com.engapp.SecurityService.mapper;

import com.engapp.SecurityService.dto.reponse.UserResponse;
import com.engapp.SecurityService.dto.request.UserRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    public UserResponse userRequestToUserResponse(UserRequest userRequest);
}

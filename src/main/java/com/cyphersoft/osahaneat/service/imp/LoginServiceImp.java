package com.cyphersoft.osahaneat.service.imp;

import com.cyphersoft.osahaneat.dto.UserDTO;
import com.cyphersoft.osahaneat.payload.request.SignUpRequest;

import java.util.List;

public interface LoginServiceImp {
    List<UserDTO> getAllUser();
    boolean checkLogin(String username, String password);
    boolean addUser(SignUpRequest signUpRequest);
}

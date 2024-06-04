package com.cyphersoft.osahaneat.service;

import com.cyphersoft.osahaneat.dto.UserDTO;
import com.cyphersoft.osahaneat.entity.Roles;
import com.cyphersoft.osahaneat.entity.Users;
import com.cyphersoft.osahaneat.payload.request.SignUpRequest;
import com.cyphersoft.osahaneat.repository.UserRepository;
import com.cyphersoft.osahaneat.service.imp.LoginServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LoginService implements LoginServiceImp {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;
    @Override
    public List<UserDTO> getAllUser(){
        List<Users> listUser = userRepository.findAll();
        List<UserDTO> userDTOList = new ArrayList<>();
        for(Users users: listUser){
            UserDTO userDTO = new UserDTO();
            userDTO.setId(users.getId());
            userDTO.setUserName(users.getUserName());
            userDTO.setFullname(users.getFullName());
            userDTO.setPassword(users.getPassword());
            userDTOList.add(userDTO);
        }
        return userDTOList;
    }

    @Override
    public boolean checkLogin(String username, String password) {
        Users user = userRepository.findByUserName(username);
        return passwordEncoder.matches(password,user.getPassword());

    }

    //Chức năng đăng ký
    @Override
    public boolean addUser(SignUpRequest signUpRequest) {
        Roles roles = new Roles();
//        roles.setId(signUpRequest.getRoleId());
        roles.setId(2);

        Users users = new Users();
        users.setFullName(signUpRequest.getFullname());
        users.setUserName(signUpRequest.getEmail());
        users.setPassword(signUpRequest.getPassword());
        users.setRoles(roles);
        try{
            userRepository.save(users);
            return true;
        }
        catch (Exception e){
            System.out.println("Lỗi khi lưu người dùng: " + e.getMessage());
            return false;
        }
    }
}

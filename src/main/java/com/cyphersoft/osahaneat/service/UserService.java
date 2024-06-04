package com.cyphersoft.osahaneat.service;

import com.cyphersoft.osahaneat.dto.UserDTO;
import com.cyphersoft.osahaneat.entity.Users;
import com.cyphersoft.osahaneat.repository.UserRepository;
import com.cyphersoft.osahaneat.service.imp.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class UserService implements UserServiceImp {

    @Autowired
    UserRepository userRepository;
    @Override
    public List<UserDTO> getAllUser() {
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
}

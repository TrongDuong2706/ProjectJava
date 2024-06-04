package com.cyphersoft.osahaneat.controller;

import com.cyphersoft.osahaneat.dto.UserDTO;
import com.cyphersoft.osahaneat.payload.ResponseData;
import com.cyphersoft.osahaneat.payload.request.SignUpRequest;
import com.cyphersoft.osahaneat.service.LoginService;
import com.cyphersoft.osahaneat.service.imp.LoginServiceImp;
import com.cyphersoft.osahaneat.utils.JwtUtilsHelper;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Encoder;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    LoginServiceImp loginServiceImp;

    @Autowired
    JwtUtilsHelper jwtUtilsHelper;

    @PostMapping("/signin")
    public ResponseEntity<?> signin(@RequestParam String username, @RequestParam String password){
        ResponseData responseData = new ResponseData();

        if(loginServiceImp.checkLogin(username, password)){
            String token = jwtUtilsHelper.generateToken(username);
            responseData.setDesc("Đăng nhập thành công");
            responseData.setData(token);

        }
        else{
            responseData.setDesc("Đăng nhập thất bại");
            responseData.setData("");
            responseData.setSuccess(false);
        }
       return new ResponseEntity<>(responseData, HttpStatus.OK);
    }
    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody SignUpRequest signUpRequest){
        ResponseData responseData = new ResponseData();
        responseData.setDesc("Đăng ký thành công");
        responseData.setData(loginServiceImp.addUser(signUpRequest));
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }

}

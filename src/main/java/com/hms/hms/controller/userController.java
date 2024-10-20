package com.hms.hms.controller;

import com.hms.hms.entity.AppUser;
import com.hms.hms.payload.LoginDto;
import com.hms.hms.repository.AppUserRepository;
import com.hms.hms.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/users")
public class userController {
    private AppUserRepository appUserRepository;
    private UserService userService;

    public userController(AppUserRepository appUserRepository, UserService userService) {
        this.appUserRepository = appUserRepository;
        this.userService = userService;
    }

    @PostMapping("/signup")
    public ResponseEntity<?> createUser( @RequestBody AppUser user ){

        Optional<AppUser> opUsername = appUserRepository.findByUsername(user.getUsername());
        if(opUsername.isPresent()){
            return new ResponseEntity<>("Username already taken", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        Optional<AppUser> opEmail = appUserRepository.findByEmail(user.getEmail());
        if(opEmail.isPresent()){
            return new ResponseEntity<>("Email already taken", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        String encryptedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(5));
        user.setPassword(encryptedPassword);
        AppUser savedUser = appUserRepository.save(user);
        return new ResponseEntity<>(savedUser,HttpStatus.CREATED);

    }

    @GetMapping("/message")
    public String getMessage(){
        return "hello";
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDto dto){
        boolean status = userService.verifyLogin(dto);
        if(status){
            return new ResponseEntity<>("user loggedin",HttpStatus.OK);
        }else {
            return new ResponseEntity<>("Invalid username/password",HttpStatus.FORBIDDEN);
        }
    }
}

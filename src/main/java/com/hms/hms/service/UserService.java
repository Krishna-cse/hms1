package com.hms.hms.service;

import com.hms.hms.entity.AppUser;
import com.hms.hms.payload.LoginDto;
import com.hms.hms.repository.AppUserRepository;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private AppUserRepository appUserRepository;

    public UserService(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    public String verifyLogin(LoginDto dto){
        Optional<AppUser> opUser = appUserRepository.findByUsername(dto.getUsername());
        if(opUser.isPresent()){
            AppUser appUser = opUser.get();
            if(BCrypt.checkpw(dto.getPassword(),appUser.getPassword())){
                //generate token
                String token;
                token = JWTService.generateToken(appUser.getUsername());
                return token;

            }

        }else {
            return null;
        }
        return null;
    }
}

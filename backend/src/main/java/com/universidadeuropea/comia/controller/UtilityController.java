package com.universidadeuropea.comia.controller;

import org.springframework.web.bind.annotation.RestController;

import com.universidadeuropea.comia.dto.ProfileDto;
import com.universidadeuropea.comia.dto.UserDto;
import com.universidadeuropea.comia.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
public class UtilityController {

    @Autowired
    private UserService userService;
    
    @GetMapping("/getProfile")
    public ResponseEntity<ProfileDto> getProfile() {
        UserDto userDto = userService.whoami();
        
        if(userDto!=null && userDto.getId()!=null){
            ProfileDto profilesDto = userService.getProfile(userDto);
            System.out.println("Profile: "+profilesDto.toString());
            return ResponseEntity.ok(profilesDto);
        }
        return ResponseEntity.badRequest().body(null);
    }

    @PostMapping("/saveProfile")
    public ResponseEntity<String> saveProfile(@RequestBody ProfileDto profileDto) {
        UserDto userDto = userService.whoami();
        
        if(userDto!=null && userDto.getId()!=null){
            ProfileDto profilesDto = userService.saveProfile(userDto.getId(), profileDto);
            return ResponseEntity.ok("Profile updated");
        }
        return ResponseEntity.badRequest().body("Error updating");
    }
    
    
}

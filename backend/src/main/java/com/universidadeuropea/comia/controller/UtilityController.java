package com.universidadeuropea.comia.controller;

import org.springframework.web.bind.annotation.RestController;

import com.universidadeuropea.comia.dto.ProfileDto;
import com.universidadeuropea.comia.dto.UserDto;
import com.universidadeuropea.comia.entity.VfridgeIngredient;
import com.universidadeuropea.comia.service.RecipesService;
import com.universidadeuropea.comia.service.UserService;

import java.util.Set;

import org.hibernate.mapping.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
public class UtilityController {

    @Autowired
    private UserService userService;

    @Autowired
    private RecipesService recipesService;
    
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
    
    
    @GetMapping("/getIngredientsByUser")
    public ResponseEntity<VfridgeIngredient[]> getUserIngredients() {
        UserDto userDto = userService.whoami();
        System.out.println("Getting ingredients");
        
        if(userDto!=null && userDto.getId()!=null){
            VfridgeIngredient[] ingredients = recipesService.getUserIngredients(userDto.getId());
            System.out.println(ingredients.toString());
            return ResponseEntity.ok(ingredients);
        }
        return null;
    }

    @PostMapping("/updateIngredients")
    public ResponseEntity<Void> updateUserIngredients(@RequestBody Set<VfridgeIngredient> ingredients) {
        UserDto userDto = userService.whoami();
        System.out.println(ingredients.toString());
        
        if(userDto!=null && userDto.getId()!=null){
            recipesService.updateUserIngredients(userDto.getId(), ingredients);
            return ResponseEntity.ok().build();
        }
        return null;
    }
    
}

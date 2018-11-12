package com.javalanguagezone.interviewtwitter.controller;

import com.javalanguagezone.interviewtwitter.service.UserService;
import com.javalanguagezone.interviewtwitter.service.dto.UserDTO;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Collection;
import java.util.HashSet;

@RestController
public class UserController {

  private UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping("/followers")
  public String followers(Principal principal) {
     userService.getUsersFollowers(principal);
    return "overview";
  }

  @GetMapping("/following")
  public Collection<UserDTO> following(Principal principal) {
    return userService.getUsersFollowing(principal);
  }

  @GetMapping("/overview")
  public String overview(Principal principal, Model model){

    Collection<UserDTO> userDTOS=new HashSet<>();

    model.addAttribute("followers",userService.getUsersFollowers(principal));
    model.addAttribute("following",userService.loadUserByUsername(principal.toString()));
    return "overview";
  }


}

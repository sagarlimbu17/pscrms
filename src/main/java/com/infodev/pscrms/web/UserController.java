package com.infodev.pscrms.web;


import com.infodev.pscrms.dto.UserCreationMap;
import com.infodev.pscrms.dto.UserUpdateMapper;
import com.infodev.pscrms.entities.Privilege;
import com.infodev.pscrms.entities.Role;
import com.infodev.pscrms.entities.User;
import com.infodev.pscrms.repository.UserRepository;
import com.infodev.pscrms.services.UserCreationServices;
import com.infodev.pscrms.utilities.SecurityUtils;
import com.infodev.pscrms.utilities.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.*;

@Controller
public class UserController {



    @Autowired
    private UserCreationServices userCreationServices;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/createUser")
    public String createUser(Model model){
        model.addAttribute("logggedInUser", Utils.getCurrentlyLoggedInUser());
        return  "createuser";
    }

    @PostMapping("/createUserPost")
    public String  createUserPost(Model model, UserCreationMap userMap){
        User u = new User();
        u.setEnabled(true);
        u.setUsername(userMap.getUsername());
        u.setMiddleName(userMap.getMiddleName());
        u.setLastName(userMap.getLastName());
        u.setEmail(userMap.getEmail());
        u.setFirstName(userMap.getFirstName());
        u.setPassword(SecurityUtils.passwordEncoder().encode(userMap.getPassword()));


        Privilege privilege = new Privilege();

        Privilege p = userCreationServices.createPrivilegeIfNotFound(userMap.getRole().toUpperCase()+"_PRIVILEGE");
        Role r = userCreationServices.createRoleIfNotFound("ROLE_"+userMap.getRole().toUpperCase(),Arrays.asList(privilege));
        r.setPrivileges(Arrays.asList(p));
        u.setRoles(Arrays.asList(r));

        userRepository.save(u);

        model.addAttribute("userCreationSuccess",true);
        model.addAttribute("logggedInUser", Utils.getCurrentlyLoggedInUser());
        return "createuser";
    }

    @GetMapping("/viewUsers")
    public String viewUsers(Model model) {

        List<User> users = userRepository.findAll();

        model.addAttribute("users",users);
        model.addAttribute("logggedInUser", Utils.getCurrentlyLoggedInUser());
        return "viewusers";

    }

    @GetMapping("/getUserByUserId")
    @ResponseBody
    public User getUserByUserId(Long id){
        User user = new User();
        Optional<User> u = userRepository.findById(id);
        if(u.isPresent()){
             user.setFirstName(u.get().getFirstName());
             user.setMiddleName(u.get().getMiddleName());
             user.setLastName(u.get().getLastName());
             user.setEmail(u.get().getEmail());
             user.setRoles(u.get().getRoles());
             user.setUsername(u.get().getUsername());
             user.setId(u.get().getId());
        }
        return user;
    }

    @PostMapping("/updateUserDetails")
    public String updateSingleUser(Model model,@ModelAttribute UserUpdateMapper userDetails){
        Optional<User> user = userRepository.findById(userDetails.getId());
        if(user.isPresent()){
            User userToUpdate= user.get();

            userToUpdate.setUsername(userDetails.getUsername());
            userToUpdate.setEmail(userDetails.getEmail());
            userToUpdate.setFirstName(userDetails.getFirstName());
            userToUpdate.setMiddleName(userDetails.getMiddleName());
            userToUpdate.setLastName(userDetails.getLastName());
            userRepository.save(userToUpdate);
        }
        model.addAttribute("users",userRepository.findAll());
        model.addAttribute("userUpdateSuccess",true);
        return "viewusers";
    }






}

package no.kristiania.exam.frontend.controller;


import no.kristiania.exam.backend.entity.Copy;
import no.kristiania.exam.backend.entity.Item;
import no.kristiania.exam.backend.entity.User;
import no.kristiania.exam.backend.service.CopyService;
import no.kristiania.exam.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.util.List;


 //This class is adaptation from:
 // https://github.com/arcuri82/testing_security_development_enterprise_systems/blob/master/intro/exercise-solutions/quiz-game/part-11/frontend/src/main/java/org/tsdes/intro/exercises/quizgame/frontend/controller/UserInfoController.java

@Named
@RequestScoped
public class UserInfoController {

    @Autowired
    private CopyService copyService;

    @Autowired
    private UserService userService;

    public String getUserName() {
        return ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
    }

    public User getUser(){
        return userService.findUserByUsername(getUserName());
    }

}

package com.sb.firebase.controller;

import com.sb.firebase.model.User;
import com.sb.firebase.service.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ExecutionException;

@RestController
public class CrudController {

    @Autowired
    private CrudService crudService;

    @PostMapping("/create")
    public String createUser(@RequestBody User user) throws InterruptedException, ExecutionException{
        return crudService.createUser(user);
    }

    @GetMapping("/get")
    public User getUser(@RequestParam String documentId) throws InterruptedException, ExecutionException{
        return crudService.getUser(documentId);
    }

    @PutMapping("/update")
    public String updateUser(@RequestBody User user) throws InterruptedException, ExecutionException{
        return crudService.updateUser(user);
    }

    @DeleteMapping("/delete")
    public String deleteUser(@RequestParam String documentId) throws InterruptedException, ExecutionException{
        return crudService.deleteUser(documentId);
    }
}

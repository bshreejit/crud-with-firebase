package com.sb.firebase.service;

import com.sb.firebase.model.User;

import java.util.concurrent.ExecutionException;

public interface CrudService {

    public String createUser(User user);

    public User getUser(String documentId);

    public String updateUser(String documentId, User user);

    public String deleteUser(String documentId);
}

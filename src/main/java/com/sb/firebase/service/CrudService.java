package com.sb.firebase.service;

import com.sb.firebase.model.User;

public interface CrudService {

    public String createUser(User user);

    public String getUser(String documentId);

    public String updateUser(String documentId, User user);

    public String deleteUser(String documentId);
}

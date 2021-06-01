package web.service;

import web.model.User;

import java.util.List;

public interface UserService {
    public List<User> getAllUsers();
    public User getUser(long id);
    public void addUser(User user);
    public void removeUser(long id);
    public void updateUser(User user, long id);
}

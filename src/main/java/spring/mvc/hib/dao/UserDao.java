package spring.mvc.hib.dao;

import spring.mvc.hib.models.User;

import java.util.List;

public interface UserDao {
    void addUser(User user);
    User getUserByID(Long id);
    void removeUserByID(Long id);
    List<User> getUsersList();
    void update(Long id, User user);
}

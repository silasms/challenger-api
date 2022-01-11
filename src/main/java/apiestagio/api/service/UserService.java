package apiestagio.api.service;


import apiestagio.api.model.User;

public interface UserService {
    User signup(User user);
    User singin(String email, String password);
    User update(User user);
}

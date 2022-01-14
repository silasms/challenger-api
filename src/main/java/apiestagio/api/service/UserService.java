package apiestagio.api.service;

import java.util.List;

public interface UserService<T> {
    T signup(T user);
    T signin(String email, String password);
    T update(T user);
    List<T> listAll();
    String delete(Integer id);
}

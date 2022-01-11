package apiestagio.api.service.impl;

import apiestagio.api.model.User;
import apiestagio.api.persistence.entity.UserEntity;
import apiestagio.api.persistence.persistence.UserRepository;
import apiestagio.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User signup(User user) {
        UserEntity userEntity = new UserEntity(user);
        if(userRepository.findByEmail(user.getEmail()) != null) return null;
        return new User(userRepository.save(userEntity));
    }

    @Override
    public User singin(String email, String password) {
        UserEntity userEntity = userRepository.findByPasswordEmail(email, password);
        return new User(userEntity);
    }

    @Override
    public User update(User user) {
        UserEntity userEntity = userRepository.findByPasswordEmail(user.getEmail(), user.getPassword());
        if(userEntity.getName() != null) {
            userEntity.setName(user.getName());
        }
        return new User(userRepository.saveAndFlush(userEntity));
    }
}
package apiestagio.api.controllers;

import apiestagio.api.model.User;
import apiestagio.api.service.UserService;
import apiestagio.api.service.impl.UserServiceImpl;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserServiceImpl userService;

    @PostMapping("/signup")
    private ResponseEntity signup(@RequestBody User user) {
        return ResponseEntity.ok(userService.signup(user));
    }

    @PostMapping("/signin")
    private ResponseEntity signin(@RequestBody User user) {
        return ResponseEntity.ok(userService.singin(user.getEmail(), user.getPassword()));
    }

    @PostMapping("/update")
    private ResponseEntity update(@RequestBody User user) {
        return ResponseEntity.ok(userService.update(user));
    }
}

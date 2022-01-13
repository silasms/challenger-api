package apiestagio.api.controllers;

import apiestagio.api.model.User;
import apiestagio.api.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserServiceImpl userService;

    @PostMapping("/signup")
    private ResponseEntity signup(@RequestBody User user) {
        User userRegistration = userService.signup(user);
        if(userRegistration != null) {
            return ResponseEntity.ok(userRegistration);
        }
        return ResponseEntity.internalServerError().body("Error: The user already exists");
    }

    @PostMapping("/signin")
    private ResponseEntity signin(@RequestBody User user) {
        try{
            User userLogin = userService.singin(user.getEmail(), user.getPassword());
            return ResponseEntity.ok(userLogin);
        } catch (Exception err) {
            return ResponseEntity.internalServerError().body("Error: Email or Password incorrect");
        }
    }

    @PutMapping("/update")
    private ResponseEntity update(@RequestBody User user) {
        try {
            User userUpdate = userService.update(user);
            return ResponseEntity.ok(userUpdate);
        } catch (Exception err) {
            return ResponseEntity.internalServerError().body("Error: Email or Password incorrect");
        }
    }

    @GetMapping("/listall")
    private ResponseEntity listAll() {
        return ResponseEntity.ok(userService.listAll());
    }

    @DeleteMapping("/delete/{id}")
    private ResponseEntity delete(@PathVariable Integer id) {
        return ResponseEntity.ok(userService.delete(id));
    }
}

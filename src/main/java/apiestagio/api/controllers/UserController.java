package apiestagio.api.controllers;

import apiestagio.api.model.User;
import apiestagio.api.service.impl.UserServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    private static Logger logger = LogManager.getLogger();
    @Autowired
    UserServiceImpl userService;

    @PostMapping("/signup")
    private ResponseEntity signup(@RequestBody User user) {
        User userRegistration = userService.signup(user);
        if(userRegistration != null) {
            logger.info("Successfully registered");
            return ResponseEntity.ok(userRegistration);
        }
        logger.error("Error: Not save a user");
        return ResponseEntity.internalServerError().body("Error: The user already exists");
    }

    @PostMapping("/signin")
    private ResponseEntity signin(@RequestBody User user) {
        try{
            User userLogin = userService.signin(user.getEmail(), user.getPassword());
            logger.info("Successfully login");
            return ResponseEntity.ok(userLogin);
        } catch (Exception err) {
            logger.error("Error: User not login");
            return ResponseEntity.internalServerError().body("Error: Email or Password incorrect");
        }
    }

    @PutMapping("/update")
    private ResponseEntity update(@RequestBody User user) {
        try {
            User userUpdate = userService.update(user);
            logger.info("Successfully update");
            return ResponseEntity.ok(userUpdate);
        } catch (Exception err) {
            logger.error("Error: Not user update");
            return ResponseEntity.internalServerError().body("Error: Email or Password incorrect");
        }
    }

    @GetMapping("/listall")
    private ResponseEntity listAll() {
        logger.info("Listing all...");
        return ResponseEntity.ok(userService.listAll());
    }

    @DeleteMapping("/delete/{id}")
    private ResponseEntity delete(@PathVariable Integer id) {
        logger.info("Deleting id...");
        return ResponseEntity.ok(userService.delete(id));
    }
}

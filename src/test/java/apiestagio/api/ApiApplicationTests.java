package apiestagio.api;

import apiestagio.api.model.User;
import apiestagio.api.persistence.entity.UserEntity;
import apiestagio.api.persistence.persistence.UserRepository;
import apiestagio.api.service.impl.UserServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
class ApiApplicationTests extends UserTestConfig {
	@Test
	@DisplayName("registering a user and testing the login")
	void signUpAndLoginUser() {
		UserRepository userRepository = Mockito.mock(UserRepository.class);
		UserServiceImpl userService = new UserServiceImpl(userRepository);
		User user = new User();
		user.setName("Silas");
		user.setEmail("silass@mail.com");
		user.setPassword("123456");
		UserEntity userEntity = new UserEntity();
		userEntity.setName("Silas");
		userEntity.setEmail("silass@mail.com");
		userEntity.setPassword("123456");
		Mockito.when(userRepository.save(any())).thenReturn(userEntity);
		User login = userService.signup(user);
		assertEquals("Silas", login.getName());
		Mockito.when(userRepository.findByPasswordEmail(any(), any())).thenReturn(userEntity);
		User userResponse = userService.singin(user.getEmail(), user.getPassword());
		assertEquals("Silas", userResponse.getName());
	}
}

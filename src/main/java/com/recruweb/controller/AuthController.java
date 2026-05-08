
package com.recruweb.controller;
import com.recruweb.service.UserService;
import com.recruweb.entity.User; import com.recruweb.repository.UserRepository; import org.springframework.beans.factory.annotation.Autowired; import org.springframework.web.bind.annotation.*;

@RestController @RequestMapping("/api/auth") @CrossOrigin("*") public class AuthController {

	@Autowired
	private UserService userService;

	@PostMapping("/register")
	public User register(@RequestBody User user) {
	    return userService.registerUser(user);
	}

	@PostMapping("/login")
	public String login(@RequestBody User user) {
	    return userService.loginUser(user);
	}
}
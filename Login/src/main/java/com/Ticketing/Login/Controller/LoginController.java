package com.Ticketing.Login.Controller;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Ticketing.Login.DAO.LoginDAO;
import com.Ticketing.Login.Service.LoginService;
import com.Ticketing.Login.Entity.User;

@RestController
@RequestMapping("/")
@CrossOrigin("*") 
public class LoginController {

	@Autowired
	LoginService loginService;
	
	@Autowired
	LoginDAO userDao;
	
	@RequestMapping("/")
	public String home() {
		return "Hello from Login Service";
	}
	
	@PostMapping("/login")
	public ResponseEntity<Object> loginUser(@RequestBody User user) {
		System.out.println("Inside Login");
		List<User> loginUser = userDao.findUserByEmail(user.getEmail());
//		System.out.println(user.getPassword());
		if (loginUser.size() > 0) {
//			System.out.println(loginUser.get(0).getEmail());
			if(loginUser.get(0).getPassword().equals(user.getPassword())) {
				return new ResponseEntity<>(loginUser, HttpStatus.OK);
			}
			else {
				return ResponseEntity.notFound().build();
			}
		}

		return ResponseEntity.notFound().build();
	}

	@PostMapping("/signUp")
	public ResponseEntity<Object> signup(@RequestBody User req) {
		try {
			System.out.println("Inside Sign Up!");
			String email = req.getEmail();
			String password = req.getPassword();
			String emailCheck[] = email.split("@");
			String role;
			
			// To check if the email and password are not empty
			if (email.equals("")) {
				return new ResponseEntity<>("FAILURE-EMPTY_EMAIL", HttpStatus.BAD_REQUEST);
			}
			if (password.equals("")) {
				return new ResponseEntity<>("FAILURE-EMPTY_PASSWORD", HttpStatus.BAD_REQUEST);
			}
			
			// To check if the user already exists
			List<User> users = userDao.findUserByEmail(email);
			if (users.size() > 0) {
//				System.out.println(users.get(0).getEmail());
				return new ResponseEntity<>("FAILURE-Email_Exists", HttpStatus.BAD_REQUEST);
			}

			String accessToken = String.valueOf(new Random(System.nanoTime()).nextInt(10000));
//			Date d = new Date();
//			java.sql.Timestamp datepresent = new Timestamp(d.getTime());
			User user = new User();
			user.setPassword(req.getPassword());
			user.setEmail(email);
			user.setName(req.getName());
			user.setRole(req.getRole());
			user = userDao.save(user);
			System.out.println("User saved!");
			return new ResponseEntity<>(user, HttpStatus.OK);
		}
		catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>("Bad Request", HttpStatus.BAD_REQUEST);
		}

	
	}
}

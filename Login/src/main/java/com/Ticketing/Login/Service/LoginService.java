package com.Ticketing.Login.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Ticketing.Login.DAO.LoginDAO;
import com.Ticketing.Login.Entity.User;

@Service
@Transactional
public class LoginService {

	@Autowired
	LoginDAO userDao;

	public User addUser(User user) {
		return this.userDao.save(user);
	}

	public Optional<User> getUser(int id) {
		return this.userDao.findById(id);
	}					

	public void deleteUser(int id) {
		this.userDao.deleteById(id);
	}

//	public List<User> getUserByEmail(String email){
//		return this.userDao.findUserByEmail(email);
//	}

}	


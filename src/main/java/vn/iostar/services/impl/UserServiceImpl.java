package vn.iostar.services.impl;

import java.sql.Date;

import vn.iostar.dao.IUserDao;
import vn.iostar.dao.impl.UserDaoImpl;
import vn.iostar.models.UserModel;
import vn.iostar.services.IUserService;

public class UserServiceImpl implements IUserService {

	IUserDao userDao = new UserDaoImpl();
	@Override
	public UserModel login(String username, String password) {
		// TODO Auto-generated method stub
		UserModel user = this.findByUsername(username);
		if (user != null && password.equals(user.getPassword())) {
		return user;
		}
		return null;
	}

	@Override
	public UserModel findByUsername(String username) {
		// TODO Auto-generated method stub
		return userDao.findByUsername(username);
	}

	@Override
	public void insert(UserModel user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean register(String email, String password, String username, String fullname, String phone) {
		if (userDao.checkExistUsername(username)) {
		return false;
		}
		long millis=System.currentTimeMillis(); 
		java.sql.Date date=new java.sql.Date(millis);
		userDao.insert(new UserModel(username,password,"imagesUser", fullname,email,1,phone, date));
		return true;
	}

	@Override
	public boolean checkExistEmail(String email) {
		return userDao.checkExistEmail(email);
	}

	@Override
	public boolean checkExistUsername(String username) {
		return userDao.checkExistUsername(username);
	}

	@Override
	public boolean checkExistPhone(String phone) {
		return userDao.checkExistPhone(phone);
	}
	
}

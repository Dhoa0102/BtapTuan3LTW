package vn.iostar.services;

import vn.iostar.models.UserModel;

public interface IUserService {
	UserModel login(String username,String password);
	UserModel findByUsername(String username);
	void insert(UserModel user);
	boolean register(String username, String password, String email, String fullname, String phone);
	boolean checkExistEmail(String email);
	boolean checkExistUsername(String username);
	boolean checkExistPhone(String phone);
	boolean updatePassword(String username,String password);
	boolean updatePhone(int id,String phone);
	boolean updateFullname(int id,String fullname);
	boolean updateImages(int id,String images);
}

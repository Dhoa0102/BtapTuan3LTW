package vn.iostar.dao;

import java.util.List;

import vn.iostar.models.UserModel;

public interface IUserDao {
	List<UserModel> findAll();
	UserModel findById(int id);
	UserModel findByUsername(String username);
	void insert(UserModel user);
	boolean checkExistUsername(String username);
	boolean checkExistEmail(String email);	
	boolean checkExistPhone(String phone);
	void updatePassword(String username,String password);
	void updatePhone(int id,String phone);
	void updateFullname(int id,String fullname);
	void updateImages(int id,String images);
}

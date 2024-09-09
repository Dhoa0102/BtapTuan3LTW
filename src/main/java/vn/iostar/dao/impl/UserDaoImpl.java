package vn.iostar.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.mysql.cj.jdbc.PreparedStatementWrapper;
import com.mysql.cj.xdevapi.PreparableStatement;

import vn.iostar.configs.DBConnectMySQL;
import vn.iostar.dao.IUserDao;
import vn.iostar.models.UserModel;

public class UserDaoImpl extends DBConnectMySQL implements IUserDao{

	public Connection conn=null;
	public PreparedStatement ps=null;
	public ResultSet rs=null;
	@Override
	public List<UserModel> findAll() {
		String sql="select * from users";
		List<UserModel> list= new ArrayList<>();
		try {
			conn=super.getDatabaseConnection();
			ps=conn.prepareStatement(sql);
			rs=ps.executeQuery();
			while (rs.next()) {
				list.add(
						new UserModel(
								rs.getInt("id"),
								rs.getString("username"),
								rs.getString("password"),
								rs.getString("email"),
								rs.getString("fullname"),
								rs.getString("images")
						));
			}
			return list;
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public UserModel findById(int id) {
		// TODO Auto-generated method stub
		String sql="select * from users where id=?";
		try {
			conn=super.getDatabaseConnection();
			ps=conn.prepareStatement(sql);
			ps.setInt(1, id);
			rs=ps.executeQuery();
			rs.next();
			UserModel fUser= new UserModel(
								rs.getInt("id"),
								rs.getString("username"),
								rs.getString("password"),
								rs.getString("email"),
								rs.getString("fullname"),
								rs.getString("images")
						);
			return fUser;
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void insert(UserModel user) {
		// TODO Auto-generated method stub
		String sql = "insert into users (username,email,password,fullname,images) values (?,?,?,?,?)";
		
		try {
			conn = super.getDatabaseConnection();
			
			ps = conn.prepareStatement(sql);
			
			ps.setString(1, user.getUsername());
			ps.setString(2,  user.getEmail());
			ps.setString(3,  user.getPassword());
			ps.setString(4, user.getFullname());
			ps.setString(5, user.getImages());
			ps.executeUpdate();
		}
		catch (Exception ex){
			ex.printStackTrace();
		}
	}
	public static void main(String[] args) {
		UserDaoImpl userDao=new UserDaoImpl();
		userDao.insert(new UserModel(0,"dinhhoa999","hoa856856@gmail.com","password","Nguyen Dinh Hoa","abc"));
		List<UserModel> list=userDao.findAll();
		for(UserModel user :list)
		{
			System.out.println(user);
		}
		System.out.println();
		UserModel fUser= userDao.findById(4);
		System.out.println(fUser);
	}
	
}

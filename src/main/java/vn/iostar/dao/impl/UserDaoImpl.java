package vn.iostar.dao.impl;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
								rs.getString("images"),
								rs.getString("fullname"),
								rs.getString("email"),
								rs.getInt("roleid"),
								rs.getString("phone"),
								rs.getDate("createDate")
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
								rs.getString("images"),
								rs.getString("fullname"),
								rs.getString("email"),
								rs.getInt("roleid"),
								rs.getString("phone"),
								rs.getDate("createDate")
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
		String sql = "insert into users (username,password,images,fullname,email,roleid,phone,createDate) values (?,?,?,?,?,?,?,?)";
		
		try {
			conn = super.getDatabaseConnection();
			
			ps = conn.prepareStatement(sql);
			
			ps.setString(1, user.getUsername());
			ps.setString(2,  user.getPassword());
			ps.setString(3,  user.getImages());
			ps.setString(4, user.getFullname());
			ps.setString(5, user.getEmail());
			ps.setInt(6,user.getRoleid());
			ps.setString(7, user.getPhone());
			ps.setDate(8, user.getCreateDate());
			ps.executeUpdate();
		}
		catch (Exception ex){
			ex.printStackTrace();
		}
	}

	@Override
	public UserModel findByUsername(String username) {
		// TODO Auto-generated method stub
				String sql="select * from users where username=?";
				try {
					conn=super.getDatabaseConnection();
					ps=conn.prepareStatement(sql);
					ps.setString(1, username);
					rs=ps.executeQuery();
					rs.next();
					UserModel fUser= new UserModel(
										rs.getInt("id"),
										rs.getString("username"),
										rs.getString("password"),
										rs.getString("images"),
										rs.getString("fullname"),
										rs.getString("email"),
										rs.getInt("roleid"),
										rs.getString("phone"),
										rs.getDate("createDate")
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
	public boolean checkExistUsername(String username) {
		String sql="select * from users where username=?";
		try {
			conn=super.getDatabaseConnection();
			ps=conn.prepareStatement(sql);
			ps.setString(1, username);
			rs=ps.executeQuery();
			if (!rs.next()){
				return false;
			}
			else
				return true;
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return false;
	}
	public boolean checkExistEmail(String email) {
		String sql="select * from users where email=?";
		try {
			conn=super.getDatabaseConnection();
			ps=conn.prepareStatement(sql);
			ps.setString(1, email);
			rs=ps.executeQuery();
			if (!rs.next()){
				return false;
			}
			else
				return true;
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return false;
	}
	public boolean checkExistPhone(String phone) {
		String sql="select * from users where phone=?";
		try {
			conn=super.getDatabaseConnection();
			ps=conn.prepareStatement(sql);
			ps.setString(1, phone);
			rs=ps.executeQuery();
			if (!rs.next()){
				return false;
			}
			else
				return true;
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return false;
	}
	public void updatePassword(String username,String password)
	{
		IUserDao ud = new UserDaoImpl();
		UserModel u = ud.findByUsername(username);
		String sql="UPDATE users SET password = ? WHERE (id = ?)";
		try {
			conn=super.getDatabaseConnection();
			ps=conn.prepareStatement(sql);
			ps.setString(1, password);
			ps.setInt(2, u.getId());
			ps.executeUpdate();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	@Override
	public void updatePhone(int id, String phone) {
		String sql="UPDATE users SET phone = ? WHERE (id = ?)";
		try {
			conn=super.getDatabaseConnection();
			ps=conn.prepareStatement(sql);
			ps.setString(1, phone);
			ps.setInt(2, id);
			ps.executeUpdate();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	@Override
	public void updateFullname(int id, String fullname) {
		String sql="UPDATE users SET fullname = ? WHERE (id = ?)";
		try {
			System.out.println(id);
			System.out.println(fullname);
			conn=super.getDatabaseConnection();
			ps=conn.prepareStatement(sql);
			ps.setString(1, fullname);
			ps.setInt(2, id);
			ps.executeUpdate();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
	}
	public static void main(String[] args) {
		UserDaoImpl userDao=new UserDaoImpl();
		//userDao.insert(new UserModel(0,"dinhhoa999","password","abc","Nguyen Dinh Hoa","hoa856856@gmail.com",2,"0966736337",new Date(System.currentTimeMillis())));
		List<UserModel> list=userDao.findAll();
		for(UserModel user :list)
		{
			System.out.println(user);
		}
		userDao.updatePassword("DinhHoa","1233");
	}

}

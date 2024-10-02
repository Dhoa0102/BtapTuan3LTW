package vn.iostar.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


import vn.iostar.configs.DBConnectMySQL;
import vn.iostar.dao.ICategoryDao;
import vn.iostar.models.CategoryModel;

public class CategoryDaoImpl implements ICategoryDao{
	public Connection conn=null;
	public PreparedStatement ps=null;
	public ResultSet rs=null;
	@Override
	public List<CategoryModel> findAll() {
		String sql="select * from categories";
		List<CategoryModel> list=new ArrayList<>();
		try {
			conn= DBConnectMySQL.getDatabaseConnection();
			ps= conn.prepareStatement(sql);
			rs= ps.executeQuery();
			while(rs.next()) {
				CategoryModel category= new CategoryModel();
				category.setCategoryid(rs.getInt("categoryid"));
				category.setCategoryname(rs.getString("categoryname"));
				category.setImages(rs.getString("images"));
				category.setStatus(rs.getInt("status"));
				list.add(category);
			}
			conn.close();
			ps.close();
			rs.close();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public CategoryModel findById(int id) {
		String sql="select * from categories where categoryid= ? ";
		try {
			conn= DBConnectMySQL.getDatabaseConnection();
			ps= conn.prepareStatement(sql);
			ps.setInt(1,id);
			rs= ps.executeQuery();
			CategoryModel category= new CategoryModel();
			while(rs.next()) {
				category.setCategoryid(rs.getInt("categoryid"));
				category.setCategoryname(rs.getString("categoryname"));
				category.setImages(rs.getString("images"));
				category.setStatus(rs.getInt("status"));
			}
			conn.close();
			ps.close();
			rs.close();
			return category;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void insert(CategoryModel category) {
		String sql="insert into categories (categoryname, images,status)\r\n"
				+ "values(?,?,?)";
		try {
			conn=DBConnectMySQL.getDatabaseConnection();
			ps= conn.prepareStatement(sql);
			ps.setString(1,category.getCategoryname());
			ps.setString(2,category.getImages());
			ps.setInt(3,category.getStatus());
			ps.executeUpdate();
			conn.close();
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void update(CategoryModel category) {
		String sql="update categories set categoryname=?, images=?,status=?"
				+ " where categoryid=?";
		try {
			conn=DBConnectMySQL.getDatabaseConnection();
			ps= conn.prepareStatement(sql);
			ps.setString(1,category.getCategoryname());
			ps.setString(2,category.getImages());
			ps.setInt(3,category.getStatus());
			ps.setInt(4, category.getCategoryid());
			ps.executeUpdate();
			conn.close();
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void delete(int id) {
		String sql="Delete from categories where categoryid= ?";
		try {
			conn=DBConnectMySQL.getDatabaseConnection();
			ps= conn.prepareStatement(sql);
			ps.setInt(1, id);
			ps.executeUpdate();
			conn.close();
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public List<CategoryModel> findName(String keyword) {
		String sql="select * from categories where categoryname like ?";
		List<CategoryModel> list=new ArrayList<CategoryModel>();
		try {
			conn=DBConnectMySQL.getDatabaseConnection();
			ps= conn.prepareStatement(sql);
			ps.setString(1,"%" +keyword+"%");
			rs= ps.executeQuery();
			while(rs.next()) {
				CategoryModel category= new CategoryModel();
				category.setCategoryid(rs.getInt("categoryid"));
				category.setCategoryname(rs.getString("categoryname"));
				category.setImages(rs.getString("images"));
				category.setStatus(rs.getInt("status"));
				list.add(category);
			}
			conn.close();
			ps.close();
			rs.close();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void softDelete(CategoryModel category) {
		String sql="update categories set status=?"
				+ "where categoryid=?";
		try {
			conn=DBConnectMySQL.getDatabaseConnection();
			ps= conn.prepareStatement(sql);
			ps.setInt(1, category.getStatus());
			ps.setInt(2, category.getCategoryid());
			ps.executeUpdate();
			conn.close();
			ps.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	
}

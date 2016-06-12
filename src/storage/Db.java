package storage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Db {
	//配置常量
	public static final String url="jdbc:mysql://localhost:3306/datamining?useUnicode=true&characterEncoding=utf8";
	public static final String name="com.mysql.jdbc.Driver";
	public static final String user="root";
	public static final String password="123456";
	//链接数据库变量
	public static Connection conn=null;
	//编辑sql变量
	public PreparedStatement pre=null;
	public ResultSet rs=null;
	//链接数据库函数
	public static Connection getConn(){
		try {
			Class.forName(name);
		} catch (Exception e) {
			System.out.println("装载驱动包出错");
			e.printStackTrace();
		}
		try {
			conn=DriverManager.getConnection(url,user,password);
		} catch (Exception e) {
			System.out.println("链接数据库异常");
			e.printStackTrace();
		}
		return conn;
	}
	public ResultSet select(){
		String sql="select * from main";
		try {
			pre=conn.prepareStatement(sql);

			rs=pre.executeQuery();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return rs;
	}
	//已知名字查找用户
	public int selectUser(String name){
		int id=0;
		String sql="select userId from user where name=?";
		try {
			pre=conn.prepareStatement(sql);
			pre.setString(1, name);
			rs=pre.executeQuery();
			if(rs.next()){
				id=rs.getInt("userId");
			}
			else
				System.out.println("user is not exit");
				
		} catch (Exception e) {
			System.out.println("查询出错");
			System.out.println(e.getMessage());
		}
		
		return id;
	}
	//保存用户
	public int saveUser(String loc,String prefession,int gen,String name){
		int id=0;
		String sql="insert into user (location,profession,gender,name) values (?,?,?,?)";
		//System.out.println(sql);
		try {
			pre=conn.prepareStatement(sql);
			pre.setString(1, loc);
			pre.setString(2, prefession);
			pre.setInt(3, gen);
			pre.setString(4, name);
			id=pre.executeUpdate();
		} catch (Exception e) {
			System.out.println("保存用户出错");
			System.out.println(e.getMessage());
		}
		
		return id;
	}
	//关闭链接
	public static void closeConn() throws SQLException{
		try {
			if(conn!=null)
				conn.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
	}
	
}

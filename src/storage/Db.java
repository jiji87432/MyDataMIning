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
	public static PreparedStatement pre=null;
	public static ResultSet rs=null;
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
	public static int selectUser(String name){
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
	public static int saveUser(String loc,String prefession,int gen,String name){
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
			e.printStackTrace();
		}
		
		return id;
	}
	//已知名字查找标签
	public static int selectTag(String content){
		int id=0;
		String sql="select tagId from tag where content=?";
		try {
			pre=conn.prepareStatement(sql);
			pre.setString(1, content);
			rs=pre.executeQuery();
			if(rs.next()){
				id=rs.getInt("tagId");
			}
			else
				System.out.println("tag is not exit");
				
		} catch (Exception e) {
			System.out.println("查询出错");
			System.out.println(e.getMessage());
		}
		
		return id;
	}
	//保存标签
	public static int saveTag(String content){
		int id=0;
		String sql="insert into tag (content) values (?)";
		//System.out.println(sql);
		try {
			pre=conn.prepareStatement(sql);
			pre.setString(1, content);
			id=pre.executeUpdate();
		} catch (Exception e) {
			System.out.println("保存标签出错");
			System.out.println(e.getMessage());
		}
		
		return id;
	}
	//已知名字查找话题
	public static int selectTopic(String content){
		int id=0;
		String sql="select topicId from topic where content=?";
		try {
			pre=conn.prepareStatement(sql);
			pre.setString(1, content);
			rs=pre.executeQuery();
			if(rs.next()){
				id=rs.getInt("topicId");
			}
			else
				System.out.println("topic is not exit");
				
		} catch (Exception e) {
			System.out.println("查询出错");
			System.out.println(e.getMessage());
		}
		
		return id;
	}
	//保存话题
	public static int saveTopic(String content){
		int id=0;
		String sql="insert into topic (content) values (?)";
		//System.out.println(sql);
		try {
			pre=conn.prepareStatement(sql);
			pre.setString(1, content);
			id=pre.executeUpdate();
		} catch (Exception e) {
			System.out.println("保存话题出错");
			System.out.println(e.getMessage());
		}
		
		return id;
	}
	//保存用户-话题
	public static int saveUserTopic(int userId,int topicId){
		int id=0;
		String sql="insert into user_topic (userId,topicId) values (?,?)";
		//System.out.println(sql);
		try {
			pre=conn.prepareStatement(sql);
			pre.setInt(1, userId);
			pre.setInt(2, topicId);
			id=pre.executeUpdate();
		} catch (Exception e) {
			System.out.println("保存用户-话题出错");
			System.out.println(e.getMessage());
		}
		
		return id;
	}
	//保存用户-标签
	public static int saveUserTag(int userId,int tagId){
		int id=0;
		String sql="insert into user_tag (userId,tagId) values (?,?)";
		//System.out.println(sql);
		try {
			pre=conn.prepareStatement(sql);
			pre.setInt(1, userId);
			pre.setInt(2, tagId);
			id=pre.executeUpdate();
		} catch (Exception e) {
			System.out.println("保存用户-标签出错");
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

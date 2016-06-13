package storage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Db {
	//���ó���
	public static final String url="jdbc:mysql://localhost:3306/datamining?useUnicode=true&characterEncoding=utf8";
	public static final String name="com.mysql.jdbc.Driver";
	public static final String user="root";
	public static final String password="123456";
	//�������ݿ����
	public static Connection conn=null;
	//�༭sql����
	public static PreparedStatement pre=null;
	public static ResultSet rs=null;
	//�������ݿ⺯��
	public static Connection getConn(){
		try {
			Class.forName(name);
		} catch (Exception e) {
			System.out.println("װ������������");
			e.printStackTrace();
		}
		try {
			conn=DriverManager.getConnection(url,user,password);
		} catch (Exception e) {
			System.out.println("�������ݿ��쳣");
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
	//��֪���ֲ����û�
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
			System.out.println("��ѯ����");
			System.out.println(e.getMessage());
		}
		
		return id;
	}
	//�����û�
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
			System.out.println("�����û�����");
			e.printStackTrace();
		}
		
		return id;
	}
	//��֪���ֲ��ұ�ǩ
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
			System.out.println("��ѯ����");
			System.out.println(e.getMessage());
		}
		
		return id;
	}
	//�����ǩ
	public static int saveTag(String content){
		int id=0;
		String sql="insert into tag (content) values (?)";
		//System.out.println(sql);
		try {
			pre=conn.prepareStatement(sql);
			pre.setString(1, content);
			id=pre.executeUpdate();
		} catch (Exception e) {
			System.out.println("�����ǩ����");
			System.out.println(e.getMessage());
		}
		
		return id;
	}
	//��֪���ֲ��һ���
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
			System.out.println("��ѯ����");
			System.out.println(e.getMessage());
		}
		
		return id;
	}
	//���滰��
	public static int saveTopic(String content){
		int id=0;
		String sql="insert into topic (content) values (?)";
		//System.out.println(sql);
		try {
			pre=conn.prepareStatement(sql);
			pre.setString(1, content);
			id=pre.executeUpdate();
		} catch (Exception e) {
			System.out.println("���滰�����");
			System.out.println(e.getMessage());
		}
		
		return id;
	}
	//�����û�-����
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
			System.out.println("�����û�-�������");
			System.out.println(e.getMessage());
		}
		
		return id;
	}
	//�����û�-��ǩ
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
			System.out.println("�����û�-��ǩ����");
			System.out.println(e.getMessage());
		}
		
		return id;
	}
	//�ر�����
	public static void closeConn() throws SQLException{
		try {
			if(conn!=null)
				conn.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
}

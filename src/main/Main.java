package main;

import java.sql.ResultSet;
import java.sql.SQLException;

import storage.Db;

public class Main {
	
	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		/*
		String url="http://www.zhihu.com/explore/recommendations";

		
		ExecuteMethod execu=new ExecuteMethod(url);

		for(int i=0;i<30;i++){
			new Thread(execu,String.valueOf(i)).start();
		}
		*/
		Db db=new Db();
		Db.getConn();
		try {
			/*
			ResultSet rs=db.select();
			while(rs.next()){
				 String username = rs.getString("userId");    
	             String password= rs.getString("tagId");    
	             System.out.println(username +" "+ password);    
			}
			*/
			System.out.println(db.saveUser("问问","问问",2,"问问"));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}

}

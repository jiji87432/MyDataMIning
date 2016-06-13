package main;

import java.sql.SQLException;

import storage.Db;

public class Main {
	
	public static void main(String[] args) throws SQLException {
		Db.getConn();
		String url="http://www.zhihu.com/explore/recommendations";

		
		ExecuteMethod execu=new ExecuteMethod(url);
		//CountDownLatch latch =new CountDownLatch(3);
		for(int i=0;i<50;i++){
			new Thread(execu,"thread"+String.valueOf(i)).start();
		}
		
	}
	
}

package main;

import java.sql.SQLException;

import algorithm.Forest;
import storage.Db;

public class Main {
	
	public static void main(String[] args) throws SQLException {
		//spiderZhihu();				//爬取知乎的方法
		//dataAnalysis()				//数据挖掘
		
		System.out.println("end");
	}
	public static void spiderZhihu(){
		Db.getConn();
		String url="http://www.zhihu.com/explore/recommendations";
		ExecuteMethod execu=new ExecuteMethod(url);
		//CountDownLatch latch =new CountDownLatch(3);
		for(int i=0;i<50;i++){
			new Thread(execu,"thread"+String.valueOf(i)).start();
		}
	}
	public static void dataAnalysis(){
		Forest forest=new Forest();
		forest.trainForest();
		forest.predict();
	}
	
}

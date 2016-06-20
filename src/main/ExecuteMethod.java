package main;

import java.util.ArrayList;

import spider.Regx;
import spider.SpiderQueue;
import spider.SpiderTool;
import spider.UserEntity;
import storage.DbMethod;

public class ExecuteMethod implements Runnable{
	protected SpiderQueue queue;

	public ExecuteMethod(String url) {
		// TODO Auto-generated constructor stub
		ArrayList<String> list=new ArrayList<String>();

		queue=new SpiderQueue();
		String content=SpiderTool.SendGet_client(url);
		list=SpiderTool.getWebAllUrl(content);
		for(String str:list){
			queue.addUnvisitedUrl(str);
		}
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		String url=new String();
		String content=new String();
		ArrayList<String> webUrlList=new ArrayList<String>();			//存在程序上的url队列
		ArrayList<String> tagList=new ArrayList<String>();				//这个url页面的标签
		ArrayList<String> authorList=new ArrayList<String>();			//用户的url
		
		while(true){
			if(!queue.unVisitedUrlsEmpty()){
				UserEntity user=new UserEntity();
				//同步，取url
				synchronized (this) {
					url=(String) queue.unVisitedUrlDequeue();
					queue.addVisiteUrl(url);

				}
				//取内容的方法，httpclient
				content=SpiderTool.SendGet_client(url);
				
				webUrlList.clear();
				tagList.clear();
				authorList.clear();
				//获取url
				webUrlList.addAll(SpiderTool.getWebAllUrl(content));
				tagList.addAll(SpiderTool.getAllContent(content,Regx.getContent_tag));
				authorList.addAll(SpiderTool.gerPeopleUrl(SpiderTool.getAllContent(content,Regx.getUrl_author)));
				
				
				
				
				synchronized (this) {
					
					for(String authorUrl:authorList){
						int id=0;
						user.initUser(authorUrl,tagList);
						if(user!=null)
							id=DbMethod.saveUser(user);
						System.out.println("user id:"+id);
						System.out.println(user);
					}
					if(queue.getUnVisitedUrlNum()<10000)
						for(String webUrl:webUrlList){
							queue.addUnvisitedUrl(webUrl);
						}
				}
		
			} else
				try {
					Thread.currentThread().sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
				
			
			
		}
		
	}

}

package main;

import java.util.ArrayList;
import java.util.LinkedList;

import spider.Regx;
import spider.SpiderQueue;
import spider.SpiderTool;
import spider.UserEntity;

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
		ArrayList<String> webUrlList=new ArrayList<String>();
		ArrayList<String> tagList=new ArrayList<String>();
		ArrayList<String> authorList=new ArrayList<String>();
		
		while(!queue.unVisitedUrlsEmpty()&&queue.getUnVisitedUrlNum()<10000){
			UserEntity user=new UserEntity();
			
			synchronized (this) {
				url=(String) queue.unVisitedUrlDequeue();
				queue.addVisiteUrl(url);

			}
			content=SpiderTool.SendGet_client(url);
			
			webUrlList.clear();
			tagList.clear();
			authorList.clear();
			
			webUrlList.addAll(SpiderTool.getWebAllUrl(content));
			tagList.addAll(SpiderTool.getAllContent(content,Regx.getContent_tag));
			authorList.addAll(SpiderTool.gerPeopleUrl(SpiderTool.getAllContent(content,Regx.getUrl_author)));
			
			
			
			
			synchronized (this) {
				
				for(String authorUrl:authorList){
					System.out.println(Thread.currentThread().getName());
					user.initUser(authorUrl,tagList);
				}
			}
	
				
			
			
		}
		
	}

}

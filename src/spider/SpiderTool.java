package spider;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;
/*
 * code by 邦柳
 * 爬虫主体类
 * */
public class SpiderTool {

	//get请求
	public static String SendGet_client(String url)
	{
		//System.out.println("spider(20) get info from:"+url);
		
		String ret="";
		
		try {
			HttpClient client=new HttpClient();
			HttpMethod method=new GetMethod(url);
			client.executeMethod(method);
			int code=method.getStatusCode();
			if(code==200)
				ret=method.getResponseBodyAsString();
			else
				ret="400";
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("function:SendGet_client error in class Spider");
			e.printStackTrace();
		}
		return ret;
	}

	//匹配web中的url
	public static ArrayList<String> getWebUrl(String content,String reg){
		ArrayList<String> urlList=new ArrayList<String>();
		Pattern p=Pattern.compile(reg,Pattern.DOTALL);
		Matcher m=p.matcher(content);
		while(m.find())
		{
			String str=m.group(1);
			if(!str.contains("javascript"))			//避免无效链接
			{
				if(str.indexOf("http")>=0){
					urlList.add(str);
				}
				else{
					urlList.add("http://www.zhihu.com"+str);
				}	
			}	
		}
		return urlList;
	}
	//一次性调用所有的匹配问题的正则
	public static ArrayList<String> getWebAllUrl(String content){
		ArrayList<String> urlList=new ArrayList<String>();
		for(String str:Regx.questionUrl){
			urlList.addAll(getWebUrl(content,str));
		}
		return urlList;
	}
	//匹配只出现一次的
	public static String getContent(String content,String reg)
	{
		String ret=new String();
		
		Pattern p=Pattern.compile(reg);
		Matcher m=p.matcher(content);
		if(m.find())
		{
			ret=m.group(1);
		}
		return ret;
	}
	//改为DOTALL模式，.可以匹配换行符，用于匹配名字，标签，用户
	public static ArrayList<String> getAllContent(String content,String reg)
	{
		ArrayList<String> ret=new ArrayList<String>();
		
		Pattern p=Pattern.compile(reg,Pattern.DOTALL);
		Matcher m=p.matcher(content);
		while(m.find())
		{	
			ret.add(m.group(1));
			//System.out.println(m.group(1));
		}
		return ret;
	}
	public static String checkEmpty(String str){
		if(str==null||str.equals(""))
			return "unknown";
		return str;
	}
	public static Set<String> gerPeopleUrl(ArrayList<String> list){
		Set<String> ret=new HashSet<String>();
		for(String url:list){
			if(url.contains("people"))
			{
				if(!url.contains("http"))
					url="http://www.zhihu.com"+url;
				ret.add(url);
			}
		}
		return ret;
	}
}

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
 * code by ����
 * ����������
 * */
public class SpiderTool {

	//get����
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

	//ƥ��web�е�url
	public static ArrayList<String> getWebUrl(String content,String reg){
		ArrayList<String> urlList=new ArrayList<String>();
		Pattern p=Pattern.compile(reg,Pattern.DOTALL);
		Matcher m=p.matcher(content);
		while(m.find())
		{
			String str=m.group(1);
			if(!str.contains("javascript"))			//������Ч����
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
	//һ���Ե������е�ƥ�����������
	public static ArrayList<String> getWebAllUrl(String content){
		ArrayList<String> urlList=new ArrayList<String>();
		for(String str:Regx.questionUrl){
			urlList.addAll(getWebUrl(content,str));
		}
		return urlList;
	}
	//ƥ��ֻ����һ�ε�
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
	//��ΪDOTALLģʽ��.����ƥ�任�з�������ƥ�����֣���ǩ���û�
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

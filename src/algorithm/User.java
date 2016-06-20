package algorithm;

import java.util.HashMap;
import java.util.HashSet;

public class User {
	private int mark;		//1：目标用户
	private String name;
	private String location;
	private String gender;
	private String profession;
	public HashMap<String,Integer> tag;
	public HashSet<String> topic;
	private int id;
	public double scope;
	public User() {
		// TODO Auto-generated constructor stub
		scope=0;
		mark=0;
		tag=new HashMap<String,Integer>();
		topic=new HashSet<String>();
		
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public int getMark() {
		return mark;
	}
	public void setMark(int mark) {
		this.mark = mark;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getProfession() {
		return profession;
	}
	public void setProfession(String profession) {
		this.profession = profession;
	}
	public HashMap<String, Integer> getTag() {
		return tag;
	}
	public void setTag(HashMap<String, Integer> tag) {
		this.tag = tag;
	}
	public HashSet<String> getTopic() {
		return topic;
	}
	public void setTopic(HashSet<String> topic) {
		this.topic = topic;
	}
	public double getScope() {
		return scope;
	}
	public void setScope(double scope) {
		this.scope = scope;
	}
	@Override
	public String toString() {
		return "用户名：" + name + "\r\n "
				+ "地区：" + location + ", 性别：" + gender+ ", 专业：" + profession 
				+ "\r\n 拥有标签：" + tag 
				+ "\r\n 擅长领域：" + topic 
				+ "\r\n";
	}
}

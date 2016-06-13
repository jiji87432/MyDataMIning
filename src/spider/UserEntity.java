package spider;

import java.util.ArrayList;
/*
 * code by 邦柳
 * 
 * web页面对应类
 * */
public class UserEntity {
	@Override
	public String toString() {
		return "UserEntity [tag=" + tag + ", topic=" + topic + ", gender=" + gender + ", url=" + url + ", name=" + name
				+ ", location=" + location + ", profession=" + profession + "]\n";
	}
	protected ArrayList<String> tag;
	protected ArrayList<String> topic;
	protected int gender;
	protected String url;
	protected String name;

	protected String location;
	protected String profession;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ArrayList<String> getTag() {
		return tag;
	}
	public void setTag(ArrayList<String> tag) {
		this.tag = tag;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public ArrayList<String> getTopic() {
		return topic;
	}
	public void setTopic(ArrayList<String> topic) {
		this.topic = topic;
	}
	public int getGender() {
		return gender;
	}
	public void setGender(int gender) {
		this.gender = gender;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getProfession() {
		return profession;
	}
	public void setProfession(String profession) {
		this.profession = profession;
	}
	public void initUser(String u,ArrayList<String> tag){
		String content=new String();
		content=SpiderTool.SendGet_client(u);
		ArrayList<String> name=SpiderTool.getAllContent(content,Regx.getContent_name);
		if(name.size()>0)
		{
			setName(SpiderTool.checkEmpty(name.get(0).toString()));
			String tmp_gender=SpiderTool.checkEmpty(SpiderTool.getContent(content,Regx.getContent_gender));
			if(tmp_gender.equals("icon icon-profile-male "))
				setGender(1);
			else if(tmp_gender.equals("icon icon-profile-female"))
				setGender(2);
			else
				setGender(0);
			
			setLocation(SpiderTool.checkEmpty(SpiderTool.getContent(content,Regx.getContent_location)));
			setProfession(SpiderTool.checkEmpty(SpiderTool.getContent(content,Regx.getContent_business)));
			setTopic(SpiderTool.getAllContent(content,Regx.getContent_top));
			
			setUrl(u);
			setTag(tag);
			
			//System.out.println(this);
		}

		
	}
}

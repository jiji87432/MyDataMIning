package spider;

import java.util.ArrayList;

public class Regx {
	//获得问题列表或问题页面

	//列表中获得问题页面的url
	public static String getUrl_question="question_link.+?href=\"(.+?)\".+?>";
	//获得圆桌的url
	public static String getUrl_roundTable="avatar-link.+?href=\"(.+?)\".+?>";
	//获得收藏夹
	public static String getUrl_favlists="<div class=\"content\">.+?href=\"(.+?)\".+?>";
	public static String getUrl_visible="visible-expanded.+?href=\"(.+?)\".+?>";
	public static String getUrl_editable="zm-editable-content.+?href=\"(.+?)\".+?>";
	
	//获取用户
	public static String getUrl_author="author-info.+?href=\"(.+?)\".+?>";
	//获得地点
	public static String getContent_location="location.+?title=\"(.+?)\".+?>";
	//获得职业
	public static String getContent_business="business.+?title=\"(.+?)\".+?>";
	//获取名字
	public static String getContent_name="title-section.+?<span class=\"name\">(.+?)</span>";
	//获得性别
	public static String getContent_gender="gender.+?class=\"(.+?)\".+?>";
	//icon icon-profile-male为男性，icon icon-profile-female为女性
	
	public static String getContent_top="zg-gray-darker\">(.+?)</a>";
	
	//获取标签
	public static String getContent_tag="zm-item-tag.+?>\\n(.+?)\\n</a>";
	public static ArrayList<String> questionUrl;
	
	static{
		questionUrl=new ArrayList<>();
		
		questionUrl.add(getUrl_question);
		questionUrl.add(getUrl_roundTable);
		questionUrl.add(getUrl_favlists);
		questionUrl.add(getUrl_visible);
		questionUrl.add(getUrl_editable);
	}
}

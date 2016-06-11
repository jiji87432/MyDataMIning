package spider;

import java.util.ArrayList;

public class Regx {
	//��������б������ҳ��

	//�б��л������ҳ���url
	public static String getUrl_question="question_link.+?href=\"(.+?)\".+?>";
	//���Բ����url
	public static String getUrl_roundTable="avatar-link.+?href=\"(.+?)\".+?>";
	//����ղؼ�
	public static String getUrl_favlists="<div class=\"content\">.+?href=\"(.+?)\".+?>";
	public static String getUrl_visible="visible-expanded.+?href=\"(.+?)\".+?>";
	public static String getUrl_editable="zm-editable-content.+?href=\"(.+?)\".+?>";
	
	//��ȡ�û�
	public static String getUrl_author="author-info.+?href=\"(.+?)\".+?>";
	//��õص�
	public static String getContent_location="location.+?title=\"(.+?)\".+?>";
	//���ְҵ
	public static String getContent_business="business.+?title=\"(.+?)\".+?>";
	//��ȡ����
	public static String getContent_name="title-section.+?<span class=\"name\">(.+?)</span>";
	//����Ա�
	public static String getContent_gender="gender.+?class=\"(.+?)\".+?>";
	//icon icon-profile-maleΪ���ԣ�icon icon-profile-femaleΪŮ��
	
	public static String getContent_top="zg-gray-darker\">(.+?)</a>";
	
	//��ȡ��ǩ
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

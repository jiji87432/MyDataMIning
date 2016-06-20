package algorithm;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;

import storage.Db;
import storage.FileReaderWriter;

public class UserService {
	private ArrayList<User> userList;
	public ArrayList<User> getUserList() {
		return userList;
	}
	public void setUserList(ArrayList<User> userList) {
		this.userList = userList;
	}
	public static PreparedStatement pre=null;
	public static ResultSet rs=null;
	public UserService() {
		// TODO Auto-generated constructor stub
		userList=new ArrayList<User>();
		Db.getConn();
	}
	public void init(int begin){
		int end=19000;
		//���1000���û�
		String sql="select * from user limit "+begin+","+end;
		int times=0;
		System.out.println(sql);
		try {
			pre=Db.conn.prepareStatement(sql);
			rs=pre.executeQuery();
			while(rs.next()){
				//��ʼ���û�����
				
				
				User user=new User();
				user.setName(rs.getString("name"));
				user.setLocation(rs.getString("location"));
				
				int gender=rs.getInt("gender");
				if(gender==1)
					user.setGender("��");
				else if(gender==2)
					user.setGender("Ů");
				else
					user.setGender("δ֪");
				
				user.setProfession(rs.getString("profession"));
				user.setId(rs.getInt("userId"));
				//��ʼ����ǩ
				String sql_Usertag="select tagId from user_tag where userId="+user.getId();
				pre=Db.conn.prepareStatement(sql_Usertag);
				ResultSet rsSet=pre.executeQuery();
				int tagId=0;
				while(rsSet.next())
				{	
					tagId=rsSet.getInt("tagId");
					
					String sql_tag="select content from tag where tagId="+tagId;
					pre=Db.conn.prepareStatement(sql_tag);
					ResultSet tagSet=pre.executeQuery();
					if(tagSet.next())
					{
						int t=1;
						String content=tagSet.getString("content");
						//�����������ĸӤ��ǩ,ͬ��
						if(DataSet.haitaoSet.contains(content))
						{
							content="����";
						}
							
						if(user.tag.containsKey(content))
						{
							t+=user.tag.get(content);
						}
						user.tag.put(content, t);
					}
				}
				//��ʼ���ó�����
				String sql_userTopic="select topicId from user_topic where userId="+user.getId();
				pre=Db.conn.prepareStatement(sql_userTopic);
				rsSet=pre.executeQuery();
				int topicId=0;
				while(rsSet.next())
				{
					topicId=rsSet.getInt("topicID");
					String sql_topic="select * from topic where topicId="+topicId;
					pre=Db.conn.prepareStatement(sql_topic);
					ResultSet topicSet=pre.executeQuery();
					if(topicSet.next())
					{
						user.topic.add(topicSet.getString("content"));
					}
						
				}
				
				double targetNum=0;
				if(user.tag.containsKey("����"))
				{
					targetNum=(double)user.tag.get("����");
					user.scope=(targetNum/DataSet.mid*3+targetNum/user.tag.size()*2);
					
				}
				else
					user.scope=0;
				if(user.scope>=DataSet.scope)
				{
					times++;
					user.setMark(1);
					//FileReaderWriter.writeIntoFile(user.toString(),"E:/test1.txt", true);
				}
				
				userList.add(user);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		FileReaderWriter.writeIntoFile("���"+times+"λ�û�","E:/test.txt", true);
	}
	public int countMid(){
		int count=0;
		ArrayList<Integer> list=new ArrayList<Integer>();
		for(User user:userList)
		{
			if(user.tag.containsKey("����"))
			{
				list.add(user.tag.get("����"));
			}
		}
		System.out.println(list.size());
		Collections.sort(list);
		count=list.get(list.size()/2);
		return count;
	}
	public double countScope(){
		double ret=0;
		double targetNum=0;
		double times=0;
		for(User user:userList)
		{
			targetNum=0;
			if(user.tag.containsKey("����"))
			{
				times++;
				targetNum=(double)user.tag.get("����");
				user.scope=(targetNum/DataSet.mid*3+targetNum/user.tag.size()*2);
			}
			else
				user.scope=0;
			
			ret+=user.scope;
		}
		ret/=times;
		return ret;
	}
}

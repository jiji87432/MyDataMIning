package storage;

import java.util.ArrayList;

import spider.UserEntity;

public class DbMethod {
	public static int saveUser(UserEntity user){
		int id=0,right=0;
		if(user.getName()==null||user.getName().equals("unknown"))
			return id;
		id=Db.selectUser(user.getName());
		if(id==0){
			right=Db.saveUser(user.getLocation(),user.getProfession(),user.getGender(),user.getName());
			if(right!=0){
				id=Db.selectUser(user.getName());
				saveUserTag(id, user.getTag());
				saveUserTopic(id, user.getTopic());
			}
		}
		else
		{
			saveUserTag(id, user.getTag());
		}
		return id;
	}
	protected static boolean saveUserTag(int userId,ArrayList<String>tag){
		if(userId==0||tag.size()==0)
			return false;
		
		int tagId=0,right=0;
		for(String tagStr:tag){
			tagId=0;
			tagId=Db.selectTag(tagStr);
			if(tagId==0){
				Db.saveTag(tagStr);
				tagId=Db.selectTag(tagStr);
			}
			right=Db.saveUserTag(userId, tagId);
			if(right==0)
				System.out.println("save UserTag error");
		}
		return true;
	}
	protected static boolean saveUserTopic(int userId,ArrayList<String>topic){
		if(userId==0||topic.size()==0)
			return false;
		int topicId=0,right=0;
		for(String topicStr:topic){
			topicId=0;
			topicId=Db.selectTopic(topicStr);
			if(topicId==0){
				Db.saveTopic(topicStr);
				topicId=Db.selectTopic(topicStr);
			}
			right=Db.saveUserTopic(userId, topicId);
			if(right==0)
				System.out.println("save UserTopic error");
		}
		return true;
	}
}

package algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class Forest {
	protected ArrayList<Tree> treeList;
	protected UserService userSer;
	public Forest() {
		treeList=new ArrayList<Tree>();
		userSer=new UserService();
		// TODO Auto-generated constructor stub
	}
	private double countEntropy(ArrayList<User> list,String tag)
	{
		double entropy=0,s1=0,s2=0,sy_1=0,sn_1=0,sy_2=0,sn_2=0,s=list.size();
		for(User user:list)
		{
			if(user.tag.containsKey(tag))
			{
				s1++;
				if(user.getMark()==1)
					sy_1++;
				else
					sn_1++;
			}
			else
			{
				s2++;
				if(user.getMark()==1)
					sy_2++;
				else
					sn_2++;
			}
		}
		entropy=s1/s*(MyTool.getI(sy_1/s1)+MyTool.getI(sn_1/s1))
				+s2/s*(MyTool.getI(sy_2/s2)+MyTool.getI(sn_2/s2));
		return entropy;
	}
	private String ID3(ArrayList<User> list){
		String ret=new String();
		HashMap<String,Double> entropyList=new HashMap<String,Double>(); 
		for(User user:list)
		{
			double entropy=0;
			for(String tag:user.tag.keySet())
			{
				if(!entropyList.containsKey(tag))
				{
					entropy=countEntropy(list, tag);
					entropyList.put(tag, entropy);
				}
			}
		}
		double max=0;
		for(Map.Entry<String, Double> entry:entropyList.entrySet())
		{
			if(entry.getValue()>max)
			{
				ret=entry.getKey();
				max=entry.getValue();
			}
		}
		return ret;
	}
	//0��ʾ�ڲ��в�ͬ�����1��ʾȫΪĿ���û���2��ʾȫΪ��Ŀ���û�
	private int ifend(ArrayList<User> list)
	{
		int ret=10,mark=0;
		mark=list.get(0).getId();
		for(User user:list)
		{
			if(mark!=user.getMark())
			{
				ret=0;
				break;
			}
		}
		if(ret==10)
		{
			ret=mark+1;
		}
		return ret;
	}
	//��ʼ��������
	private void initTree(int begin){
		LinkedList<Tree> nodeList=new LinkedList<Tree>();
		userSer.init(begin);
		//��ʼ�����ڵ�
		String content=ID3(userSer.getUserList());
		Tree tree=new Tree();
		tree.setContent(content);
		nodeList.add(tree);
		for(int i=0;i<userSer.getUserList().size();i++)
		{
			tree.userList.add(i);
		}
		
		while(nodeList.size()!=0)
		{
			Tree node=nodeList.getFirst();
			nodeList.removeFirst();
			//��ʼ����ǰ�ڵ���ӵ�е��û��б�
			ArrayList<User> nowList=new ArrayList<User>();
			for(int i:node.userList)
			{
				nowList.add(userSer.getUserList().get(i));
			}
			//�ж��Ƿ���Ҷ�ӽڵ�
			int end=ifend(nowList);
			if(end!=0)
			{
				if(end==1)
					node.setIfIs(true);
				else
					node.setIfIs(false);
				node.setIfLeave(true);
				continue;
			}
			//��ʼ�����ҽڵ�
			Tree left=new Tree();
			Tree right=new Tree();
			ArrayList<User> leftList=new ArrayList<User>();
			ArrayList<User> rightList=new ArrayList<User>();
			for(int i=0;i<nowList.size();i++)
			{
				User user=nowList.get(i);
				if(user.getTag().containsKey(node.getContent()))
				{
					leftList.add(user);
					left.userList.add(i);
				}	
				else
				{
					rightList.add(user);
					right.userList.add(i);
				}
			}
			left.setContent(ID3(leftList));
			right.setContent(ID3(rightList));
			nodeList.addLast(left);
			nodeList.addLast(right);
		}
		//��ӵ�����
		treeList.add(tree);
		
	}
	//ѵ�����ɭ��
	public void trainForest()
	{
		int times=10;
		while(times-->=0)
		{
			int i=(int)Math.random()*10000;
			initTree(i);
		}
	}
	
	public void predict()
	{
		for(User user:userSer.getUserList()){
			for(Tree tree:treeList)
			{
				while(tree!=null){
					//�ж��Ƿ�Ҷ�ӽڵ�
					if(tree.isIfLeave())
					{
						if(tree.isIfIs())
						{
							user.setMark(1);
							System.out.println(user);
							//TODO save data to database
						}
						else
							user.setMark(0);
					}
					if(user.getTag().containsKey(tree.getContent()))
					{
						tree=tree.getLeft();
					}
					else
						tree=tree.getRight();
				}
			}
		}
	}
}

package algorithm;

import java.util.ArrayList;

public class Tree {
	private Tree left;				//have the content
	private Tree right;				//have not the content
	private String content;
	private boolean ifLeave;
	private boolean ifIs;
	public ArrayList<Integer> userList;
	
	public Tree() {
		this.left=null;
		this.right=null;
		this.ifLeave=false;
		this.ifIs=false;				//if is target
		userList=new ArrayList<Integer>();
	}
	public Tree getLeft() {
		return left;
	}
	public void setLeft(Tree left) {
		this.left = left;
	}
	public Tree getRight() {
		return right;
	}
	public void setRight(Tree right) {
		this.right = right;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public boolean isIfLeave() {
		return ifLeave;
	}

	public void setIfLeave(boolean ifLeave) {
		this.ifLeave = ifLeave;
	}

	public ArrayList<Integer> getUserList() {
		return userList;
	}

	public void setUserList(ArrayList<Integer> userList) {
		this.userList = userList;
	}
	public boolean isIfIs() {
		return ifIs;
	}

	public void setIfIs(boolean ifIs) {
		this.ifIs = ifIs;
	}

}

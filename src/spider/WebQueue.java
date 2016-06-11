package spider;

import java.util.LinkedList;
/*
 * code by °îÁø
 * 
 * ÅÀ³æµÄ¶ÓÁĞ
 * */
public class WebQueue {
	private LinkedList<Object>queue=new LinkedList<Object>();
	public void enQueue(Object t){
		queue.addLast(t);
	}
	public Object deQueue(){
		return queue.removeFirst();
	}
	public boolean isQueueEmpty(){
		return queue.isEmpty();
	}
	public boolean contians(Object t){
		return queue.contains(t);
	}
	public boolean empty(){
		return queue.isEmpty();
	}
	public int getNum(){
		return queue.size();
	}
}

package storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileReaderWriter {
	//设置写入属性
	public static boolean createNewFile(String filePath){
		boolean ifSuccess=true;
		String filePathTurn=filePath.replaceAll("\\\\","/");
		int index=filePathTurn.lastIndexOf("/");
		String dir=filePathTurn.substring(0,index);
		File fileDir=new File(dir);
		ifSuccess=fileDir.mkdirs();
		File file=new File(filePathTurn);
		try{
			ifSuccess=file.createNewFile();
		}catch(IOException e)
		{
			ifSuccess=false;
			e.printStackTrace();
		}
		
		return ifSuccess;
	}
	//写入文件
	public static boolean writeIntoFile(String content,String filePath,boolean ifAppend){
		boolean ifSuccess=true;
		int index=filePath.lastIndexOf("/");
		String dir=filePath.substring(0,index);
		File fileDir=new File(dir);
		fileDir.mkdirs();
		File file=null;
		try{  
			file = new File(filePath);  
			file.createNewFile();  
	    } catch (IOException e) {  
	    	ifSuccess = false;  
	        e.printStackTrace();  
	    }  
		FileWriter fileWriter=null;
		try{
			fileWriter=new FileWriter(file,ifAppend);
			fileWriter.write(content);
			fileWriter.flush();
		}catch(IOException e)
		{
			ifSuccess=false;
			e.printStackTrace();
		}
		finally{
			try {
				if(fileWriter!=null)
					fileWriter.close();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		return ifSuccess;
	}
}

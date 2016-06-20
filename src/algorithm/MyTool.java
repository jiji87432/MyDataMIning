package algorithm;

public class MyTool {
	public static double getLog(double param)
	{
		double ret=0;
		ret=Math.log(param)/Math.log(2.0);
		return ret;
	}
	public static double getI(double param)
	{
		double ret=0;
		ret=getLog(param)*-param;
		return ret;
	}
}

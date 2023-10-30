package tw.com.ispan.util;

public class MyException extends Exception{

	private String msg = null;
	
	public MyException() {
		super();
	}
	public MyException(String msg){
		this.msg = msg;
	}
}

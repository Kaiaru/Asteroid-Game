package game;

public class SpaceObj {
//generic object class that has xcor, ycor and printOut variables
	
	protected int xcor;
	protected int ycor;
	protected String printOut;
	
	SpaceObj(){
		printOut = " ";
	}
	
	public void setYcor(int newCor){
		ycor = newCor;
	}
	
	public int getYcor(){
		return ycor;
	}
	
	public void setXcor(int newCor){
		xcor = newCor;
	}
	
	public int getXcor(){
		return xcor;
	}
	
	public String getPrintOut(){
		return printOut;
	}
}
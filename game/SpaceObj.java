package game;

public class SpaceObj {
	
	public int xcor;
	public int ycor;
	public String printOut;
	
	SpaceObj(){
		printOut = " ";
	}
	
	public void setYcor(int newCor){
		ycor = newCor;
	}
	
	public int getYcor(){
		return ycor;
	}
	
}
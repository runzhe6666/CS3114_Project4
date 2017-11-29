
public class Handle <T extends Comparable<? super T>>{

	private int location; //start location of a record
	
	public Handle(int locIn){
		location = locIn;
	}
	
	public int getValue(){
		return location;
	}
}

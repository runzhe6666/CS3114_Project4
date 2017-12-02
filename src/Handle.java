
public class Handle implements Comparable<Handle> {

	private int location; //start location of a record
	
	public Handle(int locIn){
		location = locIn;
	}
	
	public int getValue(){
		return location;
	}
	
	public void setValue(int locIn){
		location = locIn;
	}
    @Override
    public int compareTo(Handle other) {
        Integer a = new Integer(getValue());
        Integer b = new Integer(other.getValue());
        return a.compareTo(b);
    }
}

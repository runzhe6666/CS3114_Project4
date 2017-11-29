
public class Record {
	private byte flag;
	private int length;
	private String name;
	private Handle myHandle;
	
	public Record(byte flagIn, int lengthIn, String nameIn, int startLocation){
		flag = flagIn;
		length = lengthIn;
		name = nameIn;
		myHandle = new Handle(startLocation);
	}
	
	public byte getFlag(){
		return flag;
	}
	
	
	public void setFlag(byte flagIn){
		flag = flagIn;
	}
	
	public int getLength(){
		return length;
	}
	
	public String getName(){
		return name;
	}
	
}

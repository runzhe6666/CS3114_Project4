
public class Record {
	private byte flag;
	private int length;
	private String name;
	
	public Record(byte flagIn, int lengthIn, String nameIn){
		flag = flagIn;
		length = lengthIn;
		name = nameIn;
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

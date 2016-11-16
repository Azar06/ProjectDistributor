import java.util.*;

public class StudentGroup {
	public String name = null;
	
	public List<Integer> marks = new ArrayList<Integer>();
	
	public String toString() {
		return name + " " + markToString();
	}
	
	public String markToString() {
		String str = new String();
		str += "[";
		for(Integer mark : marks) {
			str += mark.toString() + "; ";
		}
		str += "]";
		return str;
	}
}
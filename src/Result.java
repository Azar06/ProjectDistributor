public class Result {
	public int[] combi;
	public double injustice;
	
	public Result(int[] combi, double injustice) {
		this.combi = combi;
		this.injustice = injustice;
	}
	
	public String toString() {
		String str = new String();
		str += "[";
		for(int i = 0; i < combi.length - 1; i++) {
			str += (combi[i] + "; ");
		}
		if(combi.length > 0) {
			str += combi[combi.length - 1];
		}
		str += "]";
		return str;
	}
}

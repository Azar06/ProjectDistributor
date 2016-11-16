import java.util.*;

public class ResultStorage {
	
	private static final int defaultNumberOfBestResult = 3;
	
	/**
	 * number of distinct 
	 */
	private int numberOfBestResult = 3;
	private int currentNumberOfResult = 0;
	private double maxStoreInjustice = 0;
	
	/**
	 * Mapping from the injustice to a list of results
	 */
	private Map<Double, List<Result>> bestResults = new HashMap<Double, List<Result>>();
	
	public ResultStorage() {
		this(defaultNumberOfBestResult);
	}
	
	public ResultStorage(int numberOfBestResult) {
		this.numberOfBestResult = numberOfBestResult;
	}
	
	/**
	 * try to add the combi in the storage 
	 * if the injustice is less than the max injustice recorded the line will be record
	 */
	public void setResult(int[] combi, double injustice) {
		if(injustice <= maxStoreInjustice || currentNumberOfResult < numberOfBestResult) {
			Result newCombi = new Result(combi, injustice);
			List<Result> res = bestResults.get(injustice);
			if(res != null) {
				res.add(newCombi);
			}
			else {
				if(bestResults.size() >= numberOfBestResult) {
					bestResults.remove(maxStoreInjustice);
				}
				else {
					currentNumberOfResult++;
				}
				List<Result> l = new ArrayList<Result>();
				l.add(newCombi);
				bestResults.put(injustice, l);
				maxStoreInjustice = this.findMaxStoreInjustice();
			}
		}
	}
	
	// return the max injustice in the recorded results
	private double findMaxStoreInjustice() {
		double d = 0;
		for(double val : bestResults.keySet()) {
			if(val > d) {
				d = val;
			}
		}
		return d;
	}
	
	// return the current results
	public Map<Double, List<Result>> getBestResults() {
		return this.bestResults;
	}
	
	public List<Result> getResults() {
		List<Double> sortedKeys = this.sort(this.bestResults.keySet());
		return this.bestResults.get(sortedKeys.get(0));
	}
	
	public double getMinInjustice() {
		List<Double> sortedKeys = this.sort(this.bestResults.keySet());
		return sortedKeys.get(0);
	}
	
	public String toString() {
		String str = new String();
		List<Double> sortedKeys = this.sort(this.bestResults.keySet());
		for(Double key : sortedKeys) {
			str += "Injustice : " + key.toString() + "\r\n";
			for(Result result : this.bestResults.get(key)) {
				str += result.toString() + "\r\n";
			}
		}
		return str;
	}
	
	private List<Double> sort(Set<Double> set) {
		List<Double> list = new ArrayList<Double>();
		for(Double d : set) {
			list.add(d);
		}
		Collections.sort(list);
		return list;
	}
}

import java.util.*;

public class ProjectDistributor {
	
	private int nbProject;
	private int[] combi;
	private List<StudentGroup> studentGroups;
	
	private TimeAnalyser timeAnalyser;
	
	public ProjectDistributor(List<StudentGroup> studentGroups) {
		// Init
		this.studentGroups = studentGroups;
		this.nbProject = studentGroups.get(0).marks.size();
		this.combi = new int[studentGroups.size()];
		for(int i = 0; i < combi.length; i++) {
			combi[i] = i;
		}
	}
	
	public ResultStorage calcul() {
		ResultStorage results = new ResultStorage(1);

		// create and init the timer
		// just to know when the algo will finish
		timeAnalyser = new TimeAnalyser();
		
		boolean continu = true;
		
		while(continu) {
			// calculate the injustice
			double injustice = getInjustice(combi, studentGroups);
			
			results.setResult(combi, injustice);

			continu = !isLastCombi(combi, nbProject);
			if(continu) {
				combi = nextValidCombi(combi, nbProject);
			}
		}
		return results;
	}
	
	/**
	 * return the injustice of the combination<br/>
	 * the combination injustice = sum of each group injustice<br/>
	 * the group injustice = Square of (max mark - group mark) (ex: if group mark = 5, group injustice = 0, ...)
	 */
	public double getInjustice(int[] combi, List<StudentGroup> studentGroups) {
		double injustice = 0;
		
		Iterator<StudentGroup> it = studentGroups.iterator();
		for(int i = 0; i < combi.length; i++) {
			StudentGroup currentGroup = it.next();
			double individualInjustice = Application.maxMark - currentGroup.marks.get(combi[i]);
			individualInjustice = Math.pow(individualInjustice, 2);
			injustice += individualInjustice;
		}
		
		return injustice;
	}
	
	/**
	 * Check if it's the last combination
	 */
	public boolean isLastCombi(int[] combi, int nbProject) {
		for(int i = 0; i < combi.length; i++) {
			if(combi[i] != nbProject - i - 1) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * return the next valid combi
	 */
	public int[] nextValidCombi(int[] combi, int nbProject) {
		int[] nextCombi = nextCombi(combi, nbProject);
		while(!isValid(nextCombi)) {
			
			// Refresh the timer
			timeAnalyser.refresh(nextCombi, nbProject);
			
			// go to the next combi
			nextCombi = nextCombi(nextCombi, nbProject);
		}
		return nextCombi;
	}
	
	/**
	 * return the next combi (don't check if one projet is already affected to another group
	 */
	public int[] nextCombi(int[] combi, int nbProject) {
		combi = combi.clone();
		int index = combi.length - 1;
		combi[index] ++;
		
		while(combi[index] == nbProject) {
			combi[index] = 0;
			index--;
			combi[index] ++;
		}
		return combi;
	}
	
	/**
	 *  check if each project is affected only once
	 */
	public boolean isValid(int[] combi) {
		for(int i = 0; i < combi.length; i++) {
			for(int j = 0; j < combi.length; j++) {
				if(j != i && combi[i] == combi[j]) {
					return false;
				}
			}
		}
		return true;
	}
	
	/*
	private String combiToString(int[] combi) {
		String str = "[";
		for(int i : combi){
			str += (i + "; ");
		}
		return str + "]";
	}
	*/
	
	/**
	 *  convert the current combination to a percentage
	 */
	public static double combiToPercent(int[] combi, int nbProject) {
		double percent = 0d;
		double d = nbProject;
		for(int i : combi) {
			percent += (double)(i / d);
			d *= nbProject;
		}
		return percent * 100;
	}
}

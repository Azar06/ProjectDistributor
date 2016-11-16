import java.io.PrintWriter;
import java.util.*;

public class Application {

	public static int maxMark = 5;
	
	public static void main(String[] args) {
		String report = new String();
		
		// Get the group
		List<StudentGroup> groups = getRandomStudentGroups();
		// List<StudentGroup> groups = getStudentGroups();
		for(StudentGroup group : groups) {
			report += (group.toString() + "\r\n");
		}
		report += "\r\n";
		
		// create the distributor
		ProjectDistributor  distributor = new ProjectDistributor(groups);
		// run the distributor
		ResultStorage resultStorage = distributor.calcul();
		
		report += resultStorage.toString() + "\r\n";
		
		List<Result> results = resultStorage.getResults();
		int size = results.size();
		Result result = results.get(0);
		
		if(size > 1) {
			// random select between the solutions with the min injustice
			long seed = 42 * (size + 1) * (long)resultStorage.getMinInjustice() + 1;
			Random rand = new Random(seed);
			int index = rand.nextInt(size);
			result = (results.get(index));
			report += "random index : " + index + "; Injustice : " + result.injustice + "\r\n";
			report += "Solution = " + result.toString() + "\r\n";
		}
		for(int i = 0; i < groups.size(); i++) {
			report += groups.get(i).name + " : Projet = " + result.combi[i] + " ; Note = " + groups.get(i).marks.get(result.combi[i]) + "\r\n";
		}
		
		System.out.println("\n\n" + report);
		
		try{
		    PrintWriter writer = new PrintWriter("C:\\Users\\zarag\\Desktop\\result.txt", "UTF-8");
		    writer.println(report);
		    writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static List<StudentGroup> getStudentGroups() {
		List<StudentGroup> groups = new ArrayList<StudentGroup>();
		// TODO
		// fill with the data from the survey
		return groups;
	}
	
	public static List<StudentGroup> getRandomStudentGroups() {
		// Init
		Random rand = new Random();
		int nbGroups = 9;
		int nbProject = 9;
		
		List<StudentGroup> groups = new ArrayList<StudentGroup>();
		for(int i = 0; i < nbGroups; i++) {
			StudentGroup group = new StudentGroup();
			group.name = "Group " + i;
			
			List<Integer> marks = new ArrayList<Integer>();
			for(int j = 0; j < nbProject; j++) {
				marks.add(rand.nextInt(maxMark + 1));
			}
			group.marks = marks;
			groups.add(group);
		}
		return groups;
	}
}

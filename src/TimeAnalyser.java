import java.text.DecimalFormat;
import java.util.Date;

public class TimeAnalyser {
	
	private static DecimalFormat percentFormat = new DecimalFormat("#.00");

	private Date date = new Date();
	private double percent = 0;
	
	// update the percent and display the progress
	public void refresh(int[] combi, int nbProject) {
		long diff1 = (new Date()).getTime() - date.getTime();
		// write the new result each 2 seconds
		if(diff1 > 2000) {
			
			double percent = ProjectDistributor.combiToPercent(combi, nbProject);
			
			Date oldDate = date;
			date = new Date();
			long diff = date.getTime() - oldDate.getTime();
			double oldPercent = this.percent;
			this.percent = percent;
			long neededTime = (long)(diff / (percent - oldPercent) * (100d - percent));
			
			System.out.println(percentFormat.format(percent) + " % : " + timeToString(neededTime));
		}
	}
	
	public String timeToString(long time) {
		time /= 1000;
		int seconds = (int) (time % 60);
		time /= 60;
		int minutes = (int) (time % 60);
		time /= 60;
		long hours = time;
		return hours + "h " + minutes + "m " + seconds + "s";
	}
}

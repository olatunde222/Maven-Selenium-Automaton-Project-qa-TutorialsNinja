package timeStampChangeExperiment;

import java.util.Date;

public class timeDemo {

	public static void main(String[] args) {
		Date date = new Date();
//		System.out.println(date.toString().replace(" ", "_").replace(":", "_"));
		String actualDate = date.toString();
		System.out.println(actualDate);
		String dateWithoutSpace = actualDate.replace(" ", "_");
		System.out.println(dateWithoutSpace);
		String dateWithoutSpaceAndColon = dateWithoutSpace.replace(":", "_");
		System.out.println(dateWithoutSpaceAndColon);


	}

}

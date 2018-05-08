package hello;

import java.util.Arrays;

public class Main {
	public static void main(String[] args) {

		TeamBuilder team = new TeamBuilder();
		if (args.length > 0) {
			final int[] results = team.specialLocation(args);
			System.out.println(Arrays.toString(results));
		}
	}
}

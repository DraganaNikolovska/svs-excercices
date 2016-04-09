/*
 * Author: Dragana
 */
public class Numbers {
	public static void main(String[] args) {

		if (args.length == 0) {
			System.out.println("Argument list is empty");
			return;
		}

		String result = "";
		for (int i = 0; i < args.length; i++) {
			switch (args[i]) {
			case "zero":
				result += 0;
				break;
			case "one":
				result += 1;
				break;
			case "two":
				result += 2;
				break;
			case "three":
				result += 3;
				break;
			case "four":
				result += 4;
				break;
			case "five":
				result += 5;
				break;
			case "six":
				result += 6;
				break;
			case "seven":
				result += 7;
				break;
			case "eight":
				result += 8;
				break;
			case "nine":
				result += 9;
			}
		}
		System.out.println(Integer.valueOf(result));
	}
}

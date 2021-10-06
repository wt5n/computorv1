package computorv1;

import java.util.Arrays;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString
public class Solver {

	double x0;
	double x1;
	double x2;
	int degree;

	public Solver(String args) {
		x0 = 0;
		x1 = 0;
		x2 = 0;
		degree = 0;
		parse(args);
	}

	public void solve() {
		switch (degree) {
			case 1:
				solveFirstDegree();
				break;
			case 2:
				solveSecondDegree();
				break;
		}
	}

	public void printResults() {
		switch (degree) {
			case 0:
			case 1:
			case 2:
			default:
		}
	}

	private void parse(String args) {
		boolean flag = false;
		if (args.length() < 5 || !args.contains("x^")) {
			throw new RuntimeException("Incorrect parameter. Example: 3 * X^0 - 6 * X^1 + 3 * X^2 = 0");
		}
		String[] arr = args.toLowerCase().split(" ");
		Arrays.stream(arr).filter(str -> str.contains("x^")).forEach(str -> {
			if (Integer.parseInt(str.substring(2)) < 0 || Integer.parseInt(str.substring(2)) > 2) {
				throw new RuntimeException(String.format("Unsolvable degree of polynomial: %d",
						Integer.parseInt(str.substring(2))));
			}
			if (degree < Integer.parseInt(str.substring(2))) {
				degree = Integer.parseInt(str.substring(2));
			}
		});
		for (int i = 0; i < arr.length; i++) {
			switch (arr[i]) {
				case "x^0":
					x0 += calculateParameter(arr, i, flag);
					break;
				case "x^1" :
				case "x":
					x1 += calculateParameter(arr, i, flag);
					break;
				case "x^2":
					x2 += calculateParameter(arr, i, flag);
					break;
				case "=":
					flag = true;
					break;
				default:
					checkDigits(arr, i, flag);
			}
		}
	}

	private void checkDigits(String[] arr, int i, boolean flag) {
		try {
			double a = Double.parseDouble(arr[i]) * (flag ? -1 : 1);
			if (i + 1 == arr.length || (i + 1 < arr.length && !arr[i + 1].equals("*")))
				x0 += a;
		} catch (NumberFormatException ignored) {}
	}

	private double calculateParameter(String[] arr, int i, boolean flag) {
		double res = 1;
		if (i > 0 && arr[i - 1].equals("*")) {
			if (i - 2 > 0 && arr[i - 3].equals("-")) {
				res = Double.parseDouble(arr[i - 2]) * -1;
			} else {
				res = Double.parseDouble(arr[i - 2]);
			}
		}
		return res * (flag ? -1 : 1);
	}

	private void solveFirstDegree() {
		double res = -1 * x0 / x1;
		System.out.printf("%.2f\n", res);
	}

	private void solveSecondDegree() {
		double discriminant = discriminant();
		if (discriminant == 0) {
			solveFirstDegree();
		} else if (discriminant > 0) {
			double sqrt = Math.sqrt(discriminant);
			double res1 = (-1 * x1 + sqrt) / (x2 * 2);
			double res2 = (-1 * x1 - sqrt) / (x2 * 2);
			System.out.printf("x1 = %.2f, x2 = %.2f%n", res1, res2);
		} else if (discriminant < 0) {
			System.out.println("unlucky");
		}
	}

	private double discriminant() {
		return Math.pow(x1, 2) - 4 * x0 * x2;
	}
}

package computorv1;

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
	String args;
	double x0;
	double x1;
	double x2;

	public Solver(String args) {
		this.args = args;
		x0 = 0;
		x1 = 0;
		x2 = 0;
	}
	// "5 * X^0 - 6 * X^1 + X^2 = 0"
	public void parse() {
		boolean flag = false;
		String[] arr = args.toLowerCase().split(" ");
		for (int i = 0; i < arr.length; i++) {
			switch (arr[i]) {
				case "x^0":
					x0 += calculateParameter(arr, i, flag);
					break;
				case "x^1":
					x1 += calculateParameter(arr, i, flag);
					break;
				case "x^2":
					x2 += calculateParameter(arr, i, flag);
					break;
				case "=":
					flag = true;
					break;
				default:
			}
		}
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

	public void solve() {
		if (x1 * x1 - 4 * x0 * x2 >= 0) {

		} else {
			System.out.println("Unsolvable");
		}
	}

	public void printResults() {
		StringBuilder reducedForm = new StringBuilder();
		if (x0 != 0) {
			reducedForm.append(x0);
			reducedForm.append(" * X^0 ");
		}
		if (x1 != 0) {
			reducedForm.append(x1);
			reducedForm.append(" * X^1 ");
		}
		if (x2 != 0) {
			reducedForm.append(x2);
			reducedForm.append(" * X^2 ");
		}
	}
}

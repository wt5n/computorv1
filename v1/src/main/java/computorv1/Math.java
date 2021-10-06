package computorv1;

public class Math {

	public static double pow(double a, int b) {
		double res = 1;
		if (b == 0) {
			return 1;
		}
		for (int i = 0; i < b; i++) {
			res *= a;
		}
		return res;
	}

	public static double sqrt(double a) {
		if (a <= 0) {
			return 0;
		}
		double res;
		double last = 0.0;
		for (res = 1; res != last; res = (res + a / res) / 2) {
			last = res;
		}
		return res;
	}
}

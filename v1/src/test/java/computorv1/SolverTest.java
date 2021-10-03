package computorv1;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class SolverTest {

	@ParameterizedTest
	@ValueSource(strings =
			{
					"5 * X^0 - 6 * X^1 + 1 * X^2 = 0",
					"3 * X^0 - 6 * X^1 + 3 * X^2 = 0",
					"7 * X^0 - 6 * X^1 + 5 * X^2 = 0"
			})
	void solveTest(String str) {
		Solver solver = new Solver(str);
		solver.solve();
	}

	@ParameterizedTest
	@ValueSource(strings =
			{
					"5 * X^0 - 6 * X^1 = 0",
					"3 * X^0 = 6 * X^1",
					"77 * X^0 = -11 * X^1",
					"0 = x^1 - 6 * x^0"
			})
	void solveSimpleTest(String str) {
		Solver solver = new Solver(str);
		solver.solve();
	}
}
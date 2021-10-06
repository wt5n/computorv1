package computorv1;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class SolverTest {

	@ParameterizedTest
	@ValueSource(strings = {
			"5 * X^0 - 6 * X^1 + 1 * X^2 = 0",
			"3 * X^0 - 6 * X^1 + 3 * X^2 = 0",
	})
	void solveSecondDegreeTest(String str) {
		Solver solver = new Solver(str);
		solver.solve();
	}

	@ParameterizedTest
	@ValueSource(strings = {
			"5 * X^0 - 6 * X^1 = 0",
			"3 * X^0 = 6 * X^1",
			"77 * X^0 = -11 * X^1",
			"0 = x^1 - 6 * x^0"
	})
	void solveSimpleTest(String str) {
		Solver solver = new Solver(str);
		solver.solve();
	}

	@ParameterizedTest
	@ValueSource(strings = {
			"5 * X^-1 - 6 * X^1 = 0",
			"3 * X^3 = 6 * X^0"
	})
	void solveThrowExceptionTest(String str) {
		Solver solver = new Solver(str);
		solver.solve();
	}

	@ParameterizedTest
	@ValueSource(strings = {
			"-10 = x^2 - 6 * x^1",
			"7 * X^0 - 6 * X^1 + 5 * X^2 = 0"
	})
	void solveUnsolvableTest(String str) {
		Solver solver = new Solver(str);
		solver.solve();
	}

	@ParameterizedTest
	@ValueSource(strings = {
			"5 * X^0 + 13 * X^1 + 3 * X^2 = 1 * X^0 + 1 * X^1",
			"6 * X^0 + 11 * X^1 + 5 * X^2 = 1 * X^0 + 1 * X^1",
			"5 * X^0 + 3 * X^1 + 3 * X^2 = 1 * X^0 + 0 * X^1"
	})
	void solveTestFromCheckList(String str) {
		Solver solver = new Solver(str);
		solver.solve();
	}
}
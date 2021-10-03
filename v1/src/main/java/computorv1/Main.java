package computorv1;

public class Main {

	public static void main(String[] args) {
		if (args.length != 1) {
			throw new IllegalArgumentException("No args");
		}

		Solver solver = new Solver(args[0]);
		solver.solve();
		System.out.println(solver);
//		solver.printResults();
	}
}

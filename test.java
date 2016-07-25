
class Test {

	public static void main(String sd[]) throws Exception {

		ProcessProgram pp = new ProcessProgram();
		System.out.println("\nOutput of First pass: \n");
		pp.firstPass();
		System.out.println("\n\nOutput of Second pass: \n");

		try {
			pp.secondPass();
			pp.printSpass();
		} catch (Exception e) {
			System.out
					.println("Stopping Execution ...!!!!!\n Variable(LABEL) is not defined in the program input file");
		}

	}

}

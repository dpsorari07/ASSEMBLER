import java.io.*;
import java.util.*;

/*
 * Class ProcessProgram processes the program from input file "input.txt".
 * Author : Deepak Prakash Sorari
 * */
class ProcessProgram {
	private int PC; // for location counter
	BufferedReader br, sr;
	InstructionTable it;
	int org;
	FileReader f;
	ArrayList<String> fin; // used to store final hexadecimal values of opCodes

	ProcessProgram() throws Exception {
		PC = 0;

		br = new BufferedReader(new FileReader("input.txt"));

		fin = new ArrayList<String>();
		it = new InstructionTable();
		org = 0;
	}

	/*
	 * firstPass() reads the input file and updates the Address Symbol Table
	 * (implemented as HashMap in InstructionTable class) it uses fProcess() to
	 * process each word in a line it then prints the entire program with
	 * address
	 */
	void firstPass() throws Exception {

		it.insertInstructions();

		Boolean b;

		String s;
		try {
			while (true) {
				s = br.readLine();

				if (s != null) {
					b = fProcess(s.trim());

					if (b == false)
						break;

				}

			}
		} catch (Exception e) {
			System.out.println("End of file is reached");
		}
		br.close();
	}

	Boolean fProcess(String sen) {
		int i;
		String ins[] = sen.split("\\s+");

		if (ins[0].equals("END")) {

			System.out.println("END");
			return false;
		}
		if (ins[0].equals("ORG")) {
			org = Integer.parseInt(ins[1]);
			PC = org;
			System.out.println(sen);
		} else {

			printFpass(sen, PC);

			char s[] = new char[12];
			s = ins[0].toCharArray();

			i = s.length - 1;

			if (s[i] == ',') {

				it.AST.put(ins[0].substring(0, i), PC);

			}
			PC = PC + 1;
		}

		return true;

	}

	/*
	 * secondPass() converts the line (other than the one's having pseudocodes)
	 * into hexadecimal format of opcode and prints the same to the console
	 */
	void secondPass() throws Exception {
		br = new BufferedReader(new FileReader("input.txt"));
		String s;
		Boolean b;

		while (true) {
			s = br.readLine();

			if (s != null) {
				b = sProcess(s.trim());

				if (b == false)
					break;

			}
		}
	}

	Boolean sProcess(String sen) throws Exception {
		int i;
		String ins[] = sen.split("\\s+");
		i = ins[0].length() - 1;
		String mcode = "";

		if (ins[0].equals("END")) {
			return false;
		}
		if (ins[0].equals("ORG")) {
			System.out.println("Program started\n");
			return true;
		}
		if (it.AST.get(ins[0].substring(0, i)) == null) {
			if (it.MRI.get(ins[0]) != null) {
				int code = it.MRI.get(ins[0]);

				try {

					if (ins[2].equals("I")) {
						code = code + 8;

					}
				} catch (Exception e) {
				}
				mcode = Integer.toHexString(code) + "";

				if (it.AST.get(ins[1]) < 16)
					mcode = mcode + "00" + Integer.toHexString(it.AST.get(ins[1])) + "";
				else if (it.AST.get(ins[1]) < 256)
					mcode = mcode + "0" + Integer.toHexString(it.AST.get(ins[1])) + "";
				else
					mcode = mcode + Integer.toHexString(it.AST.get(ins[1])) + "";
			} else {

				mcode = Integer.toHexString(it.NonMRI.get(ins[0])) + "";

			}

		} else {
			if (ins[1].equals("DEC")) {
				if (Integer.parseInt(ins[2]) < 16)
					mcode = mcode + "000" + (Integer.toHexString(Integer.parseInt(ins[2]))) + "";
				else if (Integer.parseInt(ins[2]) < 256)
					mcode = mcode + "00" + Integer.toHexString(Integer.parseInt(ins[2])) + "";
				else if (Integer.parseInt(ins[2]) < 4096)
					mcode = mcode + "0" + Integer.toHexString(Integer.parseInt(ins[2])) + "";
				else
					mcode = mcode + Integer.toHexString(Integer.parseInt(ins[2])) + "";

			} else if (ins[1].equals("HEX")) {
				mcode = ins[2];

			}
		}

		fin.add(mcode);

		return true;
	}

	void printFpass(String sn, int PC) {
		System.out.println(PC + "\t" + sn);
	}

	void printSpass() {
		System.out.println("Address \t Opcode");
		PC = org;
		for (String s : fin) {
			System.out.print(PC + "   \t \t");
			System.out.println(s);
			PC = PC + 1;
		}

	}

}

import java.util.*;

class InstructionTable {
	HashMap<String, Integer> MRI = new HashMap<String, Integer>();
	HashMap<String, Integer> NonMRI = new HashMap<String, Integer>();
	HashMap<String, Integer> AST = new HashMap<String, Integer>();

	void insertInstructions() {
		MRI.put("AND", 0x0);
		MRI.put("ADD", 0x1);
		MRI.put("LDA", 0x2);
		MRI.put("STA", 0x3);
		MRI.put("BUN", 0x4);
		MRI.put("BSA", 0x5);
		MRI.put("ISZ", 0x6);

		NonMRI.put("CLA", 0x7800);
		NonMRI.put("CLE", 0x7400);
		NonMRI.put("CMA", 0x7200);
		NonMRI.put("CME", 0x7100);
		NonMRI.put("CIR", 0x7080);
		NonMRI.put("CIL", 0x7040);
		NonMRI.put("INC", 0x7020);
		NonMRI.put("SPA", 0x7010);
		NonMRI.put("SNA", 0x7008);
		NonMRI.put("SZA", 0x7004);
		NonMRI.put("SZE", 0x7002);
		NonMRI.put("HLT", 0x7001);

		NonMRI.put("INP", 0xf800);
		NonMRI.put("OUT", 0xf400);
		NonMRI.put("SKI", 0xf200);
		NonMRI.put("SKO", 0xf100);
		NonMRI.put("ION", 0xf080);
		NonMRI.put("IOF", 0xf040);
	}

}

import java.util.*;
import java.io.*;

public class SRTToScript {
    public static void main(String[] args) {
    	BufferedReader in;
    	File file;
    	String output;
    	try {
    	if(args.length > 1) {
            file = new File(args[0]);
            in = new BufferedReader(new FileReader(file));
            output = args[1];
		} else if(args.length > 0) {
			file = new File(args[0]);
            in = new BufferedReader(new FileReader(file));
            output = file.getAbsolutePath().substring(0, file.getAbsolutePath().length()-4) + "_text.txt";
		} else {
			throw new Exception("Usage:\njava SRTToScript [input.srt] [output.txt]\njava SRTToScript [input.srt]");
		}
		String str;

		List<String> list = new ArrayList<String>();
		while((str = in.readLine()) != null){
		    list.add(str);
		}

		String[] stringArr = list.toArray(new String[0]);

		String[] sentenceSeperation = new String[]{".", "?", "!", ""};

		List<String> scriptList = new ArrayList<>();

		for (int i = 0; i < list.size(); i++) {
			try {
				Integer.parseInt(list.get(i));
				String temp = list.get(i+2);
				for (int j = i + 2; j < list.size(); j++) {
					try {
						Integer.parseInt(list.get(j + 2));
						scriptList.add(temp);
						break;
					} catch (Throwable exc) {
						temp = temp + " " + list.get(j + 1) + " ";
					}
				}
			} catch (Throwable e) {
			}
		}

 		PrintWriter writer = new PrintWriter(output, "UTF-8");

 		List<String> noLBScript = new ArrayList<>();
 		String longDump = "";

 		for (int i = 0; i < scriptList.size(); i++) {
 			longDump = longDump + scriptList.get(i);
 		}

 		longDump = longDump.replace(". ", ".");
 		longDump = longDump.replace("\n", "");
 		longDump = longDump.replace("! ", "!");
 		longDump = longDump.replace(" ♪  ", "");
 		longDump = longDump.replace("♪  ", "");
 		longDump = longDump.replace("♪ ", "");
 		longDump = longDump.replace(" ♪", "");
 		longDump = longDump.replace("♪", "");
 		longDump = longDump.replace("? ", "?");
 		longDump = longDump.replace("...", "[S@]");
 		longDump = longDump.replace("</i> <i> ", "\n");
 		longDump = longDump.replace("</i><i> ", " \n");
 		longDump = longDump.replace("</i>\n<i> ", " \n");
 		longDump = longDump.replace("</i>\n <i> ", " \n");
 		longDump = longDump.replace("</i><i>", " \n");
 		longDump = longDump.replace("</i>\n<i>", " \n");
 		longDump = longDump.replace("</i>\n <i>", " \n");
 		longDump = longDump.replace("<i> ", "");
 		longDump = longDump.replace("<i>", "");
 		longDump = longDump.replace("</i> ", " ");
 		longDump = longDump.replace("</i>", "");
 		longDump = longDump.replace("  ", " ");
 		
 		String organizedDump = "";

 		for (int i = 0; i < longDump.length(); i++) {
 			char character = longDump.charAt(i);
 			boolean shouldBreak = character == '.' || character == '?' || character == '!';
 			organizedDump = organizedDump + character;
 			if (shouldBreak) {
 				organizedDump = organizedDump + "\n";
 			}
 		}

 		organizedDump = organizedDump.replace("[S@]", "... ");
 		organizedDump = organizedDump.replace("\n ", "\n");
 		organizedDump = organizedDump.replace("\n\n", "\n");
 		organizedDump = organizedDump.replace("\n\n\n", "\n");
 		organizedDump = organizedDump.replace(":  ", ": ");

 		writer.printf(organizedDump);

		writer.close();

		System.out.println("Subtitle successfully converted to TXT script.");
		System.out.println("The converted script is exported as script.txt under the same directory.");

	} catch (Throwable excep) {
		System.out.println("Something went wrong.");
		System.out.println(excep);
		System.out.println(excep.getMessage());
	}

    }
}
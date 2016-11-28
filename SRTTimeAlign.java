import java.util.*;
import java.io.*;

public class SRTTimeAlign {
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
            output = file.getAbsolutePath().substring(0, file.getAbsolutePath().length()-4) + "_aligned.srt";
		} else {
			throw new Exception("Usage:\njava SRTTimeAlign [input.srt] [output.srt]\njava SRTTimeAlign [input.srt]");
		}
		String str;

		List<String> list = new ArrayList<String>();
		while((str = in.readLine()) != null){
		    list.add(str);
		}

		String[] stringArr = list.toArray(new String[0]);
 		String match = " --> ";
 		int lastLineOfTimecode = 1;

 		PrintWriter writer = new PrintWriter(output, "UTF-8");


		for (int i = 2; i < list.size(); i++) {
			if (list.get(i).contains(match)) {
				int pointOfBreakL = list.get(i).indexOf(match);
				int pointOfBreakR = list.get(lastLineOfTimecode).indexOf(match) + 5;
				String beginningTimeForThis = list.get(lastLineOfTimecode).substring(pointOfBreakR, list.get(lastLineOfTimecode).length());
				String brokenTimeForThis = list.get(i).substring(0, pointOfBreakL);
				String replacedString = list.get(i).replaceAll(brokenTimeForThis, beginningTimeForThis);
				int pointOfComma = replacedString.indexOf(",") + 1;
				int pointOfArrow = replacedString.indexOf(" --> ");
				String millisecondSubstring = replacedString.substring(pointOfComma, pointOfArrow);
				int millisecondPlusOne = Integer.parseInt(millisecondSubstring) + 1;
				replacedString = replacedString.substring(0, pointOfComma) + millisecondPlusOne + replacedString.substring(pointOfArrow, replacedString.length());
				list.set(i, replacedString);
				lastLineOfTimecode = i;
			}
		}

		for (int i = 0; i < list.size(); i++) {
			writer.println(list.get(i));
		}

		writer.close();

	} catch (Throwable e) {
		System.out.println("Something went wrong.");
		System.out.println(e);
		System.out.println(e.getMessage());
	}

    }
}
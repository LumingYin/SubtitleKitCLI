import java.util.*;
import java.io.*;

public class SRTLanFlip {
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
            output = file.getAbsolutePath().substring(0, file.getAbsolutePath().length()-4) + "_flipped.srt";
		} else {
			throw new Exception("Usage:\njava SRTLanFlip [input.srt] [output.srt]\njava SRTLanFlip [input.srt]");
		}
		String str;

		List<String> list = new ArrayList<String>();
		while((str = in.readLine()) != null){
		    list.add(str);
		}

		String[] stringArr = list.toArray(new String[0]);
 		CharSequence cs1 = "-->";
		for (int i = 1; i < list.size(); i++) {
			if (list.get(i-1).contains(cs1)) {
				String temp = list.get(i);
				list.set(i, list.get(i+1));
				list.set(i+1, temp);
			}
		}

		PrintWriter writer = new PrintWriter(output, "UTF-8");

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
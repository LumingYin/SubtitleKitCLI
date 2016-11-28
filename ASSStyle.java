import java.util.*;
import java.io.*;

public class ASSStyle {
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
            output = file.getAbsolutePath().substring(0, file.getAbsolutePath().length()-4) + "_stylized.ass";
		} else {
			throw new Exception("Usage:\njava ASSStyle [input.ass] [output.ass]\njava ASSStyle [input.ass]");
		}

		String str;

		List<String> list = new ArrayList<String>();
		while((str = in.readLine()) != null){
		    list.add(str);
		}

		String[] stringArr = list.toArray(new String[0]);
 		String special = "{\\WenQuanYiMicroHei}{\\fs17}{\\b0}{\\c&HFFFFFF&}{\\3c&H2F2F2F&}{\\4c&H000000&}{\\shad1}";
 		String match = "\\N";
 		int scriptInfoLine = 0;
 		int v4plusStyles = 0;

 		String[] scriptInfo = new String[]{"[Script Info]","Title:Video","Original Script:Video","Synch Point:0","ScriptType:v4.00+","Collisions:Normal","PlayResX:768","PlayResY:432","Timer:100.0000"};

		for (int i = 1; i < list.size(); i++) {
			if (list.get(i).contains("Dialogue: ")) {
				int num = list.get(i).indexOf(match);
				str = new StringBuilder(list.get(i)).insert(num + 2, special).toString();
				list.set(i, str);
			} else if (list.get(i).contains("Format: Name")) {
				list.set(i, "Format: Name, Fontname, Fontsize, PrimaryColour, SecondaryColour, OutlineColour, BackColour, Bold, Italic, Underline, StrikeOut, ScaleX, ScaleY, Spacing, Angle, BorderStyle, Outline, Shadow, Alignment, MarginL, MarginR, MarginV, Encoding");
			} else if (list.get(i).contains("Style: ")) {
				list.set(i, "Style: Default,WenQuanYiMicroHei,29,&H00FFFFFF,&HF0000000,&H00C62632,&H3C000000,0,0,0,0,100,100,0,0.00,1,3,2,2,30,30,8,134");
			} else if (list.get(i).contains("Format: Layer")) {
				list.set(i, "Format: Layer, Start, End, Style, Name, MarginL, MarginR, MarginV, Effect, Text");
			} else if (list.get(i).contains("[Script Info]")) {
				scriptInfoLine = i;
			} else if (list.get(i).contains("[V4+ Styles]")) {
				v4plusStyles = i;
			}
		}

		for (int i = scriptInfoLine; i < scriptInfoLine + scriptInfo.length; i++) {
			list.add(i, scriptInfo[i]);
		}

		int initialScriptInfoTag = 0;
		int beginToRemoveLine = 0;
		int endRemovalLine = 0;

		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).contains("[Script Info]")) {
				initialScriptInfoTag = 0;
			}
			if (list.get(i).contains("[Script Info]") && initialScriptInfoTag != i) {
				beginToRemoveLine = i;
			}
			if (list.get(i).contains("[V4+ Styles]")) {
				endRemovalLine = i - 2;
			}
		}

		for (int i = 0; i <= endRemovalLine - beginToRemoveLine; i++) {
			list.remove(beginToRemoveLine);
		}


		PrintWriter writer = new PrintWriter(output, "UTF-8");

		for (int i = 0; i < list.size(); i++) {
			writer.println(list.get(i));
		}

		writer.close();

		System.out.println("The subtitle has been successfully stylized.");
		System.out.println("The stylized subtitle file is stored as export.ass.");

	} catch(FileNotFoundException e) {
		System.out.println("==== Error ====");
		System.out.println("Cannot find subtitle file import.ass.");
		System.out.println("Place subtitle file import.ass in the same folder as this script.");
		System.out.println("If your subtitle is in .srt format, you need to convert it.");
		System.out.println("Convert it with \"ffmpeg -i subtitle.srt import.ass\"");;
	} catch (Throwable e) {
		// System.out.println("Malformatted subtitle file.");
		// System.out.println("If your subtitle is in .srt format, you need to convert it.");
		// System.out.println("Convert it with \"ffmpeg -i subtitle.srt import.ass\"");;
		// System.out.println("Debug Info:");;
		// System.out.println(e);
		System.out.println(e.getMessage());
	}

    }
}
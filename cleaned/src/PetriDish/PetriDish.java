package PetriDish;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;

public class PetriDish {
	static PetriMap map = null;
	static PetriDict petriDict;
	static ArrayList<String> srcMap = new ArrayList<String>();

	public static void main(String[] args) {
		int len;
		String inputFile;

		inputFile = args.length == 1 ? args[0] : "input.txt";
		len = init(inputFile);
		if (len < 1) {
			System.err.println("PetriDish: Loading Error.");
			return;
		}
		execAlgo();
		outputResponse();
	}

	// Actual code

	private static char[] execAlgo() {
		for (int index = 0; index < map.length; index++) {
			if (map.data[index] == '*') {
				GetShape(index);
			}
		}
		return map.data;
	}

	private static ArrayList<String> GetShape(int begPos) {
		PetriShape newShape = new PetriShape(map.width, map.height, begPos, map.data);
		char c;

		c = petriDict.checkExist(newShape);
		markShape(begPos, c);
		return null;
	}

	private static void markShape(int pos, char symbol) {
		int x;
		int y = -1;

		map.data[pos] = symbol;
		while (y <= 1) {
			if (y * map.width + pos >= 0 && y + pos / map.width < map.height) {
				x = -1;
				while (x <= 1) {
					if (pos % map.width + x >= 0 && pos % map.width + x < map.width
							&& map.data[pos + (x + (y * map.width))] == PetriShape.speChar) {
						markShape(pos + (x + (y * map.width)), symbol);
					}
					x++;
				}
			}
			y++;
		}
	}

	// Tools

	private static String buildOutSring() {
		int i = 0;
		String str = new String();

		while (i < map.length) {
			str += map.data[i++];
			if (i % map.width == 0) {
				str += '\n';
			}
		}
		return str;
	}

	private static void outputResponse() {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"));
			writer.write(buildOutSring());
			writer.close();
		} catch (IOException e) {
			System.err.println("Error during filecreation :");
			e.printStackTrace();
		}
	}
	
	// private static void printStringArray(ArrayList<String> result, String separator) {
	// 	int idx = 0;
		
	// 	if (result == null)
	// 	{
	// 		System.err.println("PetriDish: Error: Trying to print empty result.");
	// 		return;
	// 	}
	// 	while (idx < result.size()) {
	// 		System.out.print(result.get(idx++) + separator);
	// 	}
	// }

	// Preparation methods
	
	private static int init(String args) {
		ArrayList<String> srcMap;
		int len;
		
		if ((srcMap = loadMap(args)) == null || (len = getMapLen(srcMap)) == -1) {
			System.err.println("Error occured while reading the file");
			return -1;
		}
		map = new PetriMap(srcMap);
		petriDict = new PetriDict();
		return len;
	}
	
	private static int getMapLen(ArrayList<String> srcMap) {
		int width = 0;
		int idx = 0;
		
		width = srcMap.get(0).length();
		while (idx < srcMap.size()) {
			if (width != srcMap.get(idx++).length()) {
				return -1;
			}
		}
		return width;
	}
	
	private static ArrayList<String> loadMap(String filename) {
		File file = new File(filename);
		ArrayList<String> srcMap = new ArrayList<String>();
		BufferedReader content;
		String str;
		
		if (file.isFile() == false) {
			System.out.println("not a file");
			return null;
		}
		try {
			content = new BufferedReader(new FileReader(file));
			while ((str = content.readLine()) != null) {
				srcMap.add(str);
			}
			content.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return srcMap;
	}
}
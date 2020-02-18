import java.util.ArrayList;

/**
 * map
 */
public class PetriMap {
	char[] data;
	int width;
	int height;
	int length;

	public PetriMap(ArrayList<String> srcMap) {
		String tmpString = new String();

		if (srcMap == null) {
			System.err.println("PetriMap: Error: Empty String array send");
			return;
		}
		this.height = srcMap.size();
		this.width = srcMap.get(0).length();
		this.length = this.width * this.height;
		for (String str : srcMap) {
			tmpString = tmpString.concat(str);
		}
		this.data = tmpString.toCharArray();
		return;
	}

	public void printMap() {
		for (int idx = 0; idx < this.data.length; idx++) {
			System.out.print(this.data[idx]);
			if ((idx + 1) % this.width == 0) {
				System.out.println("$");
			}
		}
		System.out.println("===================================");
	}
}
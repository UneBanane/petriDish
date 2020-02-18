public class PetriShape {
	int sWidth;
	int sHeight;
	int sBegPos;
	int sEndPos;
	char[] data;
	static char speChar = '#';
	// static char letter = 'a';
	// static char endletter = 'A';
	
	public PetriShape(int mapWidth, int mapHeight, int pos, char[] map) {
		int patPos;
		
		this.sBegPos = pos;
		this.sEndPos = pos;
		getBorder(mapWidth, mapHeight, pos, map);
		this.sWidth = (this.sEndPos % mapWidth) - (this.sBegPos % mapWidth) + 1;
		this.sHeight = (this.sEndPos / mapWidth) - (this.sBegPos / mapWidth) + 1;
		
		createBlank(this.sWidth * this.sHeight);
		// System.out.print("shape size is [");
		// System.out.print(sWidth);
		// System.out.print(", ");;
		// System.out.print(sHeight);;
		// System.out.println("]");;
		
		patPos = pos % mapWidth - sBegPos % mapWidth;
		// System.out.println("Making a new pattern");
		// printShape();
		copy(map, mapWidth, pos, patPos);
		// System.out.println("New pattern = ");
		// printShape();
		// map[sBegPos] = letter++;
		// map[sEndPos] = endletter++;
		// printShape();
	}
	
	private void createBlank(int size) {
		String str = new String();
		
		while (size > 0) {
			str += ' ';
			size--;
		}
		this.data = str.toCharArray();
	}
	
	private void getBorder(int mapWidth, int mapHeight, int pos, char[] map) {
		updateSize(pos, mapWidth, mapHeight);
		int y = -1;
		int x = -1; 
		
		map[pos] = '.';
		
		while (y <= 1) {
			if (y * mapWidth + pos >= 0 && y + pos / mapWidth < mapHeight) {
				x = -1;
				while (x <= 1) {
					if (pos % mapWidth + x >= 0 && pos % mapWidth + x < mapWidth
					&& map[pos + (x + (y * mapWidth))] == '*') {
						getBorder(mapWidth, mapHeight, pos + (x + (y * mapWidth)), map);
					}
					x++;
				}
			}
			y++;
		}
	}
	
	private void updateSize(int pos, int width, int height) {
		if (pos % width < this.sBegPos % width) {
			this.sBegPos -= 1;
		}
		if (pos / width < this.sBegPos / width) {
			this.sBegPos -= width;
		}
		if (pos % width > this.sEndPos % width) {
			this.sEndPos += 1;
		}
		if (pos / width > this.sEndPos / width) {
			this.sEndPos += width;
		}
	}
	
	private void copy(char[] map, int mapWidth, int pos, int patPos) {
		int y = -1;
		int x;
		
		map[pos] = speChar;
		System.out.println("patpos = " + patPos);
		data[patPos] = speChar;
		// pattern = (((pos % mapWidth - sBegPos % mapWidth) + (pos / mapWidth - sBegPos / mapWidth) * this.sWidth + 1 >= 0) ? pattern.substring(0, (pos % mapWidth - sBegPos % mapWidth) + (pos / mapWidth - sBegPos / mapWidth) * this.sWidth + 1) : "") + '#' + (((pos % mapWidth - sBegPos % mapWidth) + (pos / mapWidth - sBegPos / mapWidth) * this.sWidth + 1 < this.sWidth * this.sHeight) ? pattern.substring((pos % mapWidth - sBegPos % mapWidth) + (pos / mapWidth - sBegPos / mapWidth) * this.sWidth + 1) : "");
		
		while (y <= 1) {
			if (y * mapWidth + pos >= 0 && y + pos / mapWidth < map.length / mapWidth) {
				x = -1;
				while (x <= 1) {
					if (pos % mapWidth + x >= 0 && pos % mapWidth + x < mapWidth
					&& map[pos + (x + (y * mapWidth))] == '.') {
						copy(map, mapWidth, pos + (x + (y * mapWidth)), patPos + (x + (y * sWidth)));
					}
					x++;
				}
			}
			y++;
		}
	}
	
	public void printShape() {
		int i = 0;
		
		System.out.println("this shape is :");
		while (i < this.sWidth * this.sHeight) {
			System.out.print(this.data[i++]);
			if (i % this.sWidth == 0) {
				System.out.println("|");
			}
		}
		System.out.println("------");
		System.out.println();
	}
}
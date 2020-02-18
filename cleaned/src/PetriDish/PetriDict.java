package PetriDish;

import java.util.ArrayList;

/**
* petriDict
*/
public class PetriDict {
	ArrayList<PetriShape> shapeDict;
	char lastchar = 'a' - 1;
	
	public PetriDict() {
		shapeDict = new ArrayList<PetriShape>();
		return;
	}
	
	public char checkExist(PetriShape shape) {
		char c = 'a';
		int idx = 0;
		
		while (idx < shapeDict.size()) {
			if (compare(shape, shapeDict.get(idx++))) {
				return c;
			}
			c++;
		}
		shapeDict.add(shape);
		return c;
	}
	
	public boolean compare(PetriShape shape, PetriShape dictShape) {
		int w = shape.sWidth;
		int h = shape.sHeight;

		if (shape.sWidth == dictShape.sWidth && shape.sHeight == dictShape.sHeight) {
			if (compareParse(0, 1, w, shape, dictShape, shape.sWidth) ||
			compareParse(w - 1, -1, w, shape, dictShape, shape.sWidth) ||
			compareParse((h - 1) * w, 1, -w, shape, dictShape, shape.sWidth) ||
			compareParse((h * w) - 1, -1, -w, shape, dictShape, shape.sWidth)) {
				return true;
			}
		}
		if (shape.sWidth == dictShape.sHeight && shape.sHeight == dictShape.sWidth) {
			if (compareParse(0, w, 1, shape, dictShape, shape.sHeight) ||
			compareParse((h - 1) * w, -w, 1, shape, dictShape, shape.sHeight) ||
			compareParse(w - 1, w, -1, shape, dictShape, shape.sHeight) ||
			compareParse((h * w) - 1, -w, -1, shape, dictShape, shape.sHeight)) {
				return true;
			}
		}
		return false;
	}
	
	public boolean compareParse(int pos, int x_inc, int y_inc, PetriShape shape, PetriShape dictShape, int divider) {
		int x = 0;
		int y = 0;
		int count = 0;
		int end = shape.sWidth * shape.sHeight;
		
		while (count < end) {
			if (dictShape.data[count] != shape.data[pos + x * x_inc + y * y_inc]) {
				return false;
			}
			count++;
			x += 1;
			if (count % divider == 0) {
				x = 0;
				y += 1;
			}
		}
		return true;
	}
}

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
		// if ((shape.sWidth != dictShape.sWidth && shape.sWidth != dictShape.sHeight)
		// || (shape.sHeight != dictShape.sWidth && shape.sHeight != dictShape.sHeight))
		// 	return false;
		// }
		System.out.println("Size of shape is : " + shape.sWidth + ", " + shape.sHeight);
		if (shape.sWidth == dictShape.sWidth && shape.sHeight == dictShape.sHeight) {
			System.out.println("Testing normal ====");
			if (compareParse(0, 1, w, shape, dictShape, shape.sWidth) ||
			compareParse(w - 1, -1, w, shape, dictShape, shape.sWidth) ||
			compareParse((h - 1) * w, 1, -w, shape, dictShape, shape.sWidth) ||
			compareParse((h * w) - 1, -1, -w, shape, dictShape, shape.sWidth)) {
				System.out.println("													>>>>>>> DONE <<<<<");
				return true;
			}
		}
		if (shape.sWidth == dictShape.sHeight && shape.sHeight == dictShape.sWidth) {
			System.out.println("Testing 90degree ====");
			if (compareParse(0, w, 1, shape, dictShape, shape.sHeight) ||
			compareParse((h - 1) * w, -w, 1, shape, dictShape, shape.sHeight) ||
			compareParse(w - 1, w, -1, shape, dictShape, shape.sHeight) ||
			compareParse((h * w) - 1, -w, -1, shape, dictShape, shape.sHeight)) {
				System.out.println("													>>>>>>> DONE <<<<<");
				return true;
			}
		}
		System.out.println("													>>>>>>> DONE <<<<<");
		return false;
	}
	
	public boolean compareParse(int pos, int x_inc, int y_inc, PetriShape shape, PetriShape dictShape, int divider) {
		int x = 0;
		int y = 0;
		int count = 0;
		int end = shape.sWidth * shape.sHeight;
		
		// System.out.println("=======");

		// System.out.println("executing on shape :");
		// shape.printShape();
		// dictShape.printShape();
		// System.out.println("x inc is : " + x_inc);
		// System.out.println("y inc is : " + y_inc);
		// System.out.println("Start at pos = " + pos);
		while (count < end) {
			// System.out.println("count = " + count + "; char is dictShape.data[" + count + "] : " + shape.data[count]);
			// System.out.println("x = " + x + "\ny = " + y);
			// System.out.println("x = " + x + "\ny = " + y + "; char is shape.data[" + (pos + x * x_inc + y * y_inc) + "] : " + dictShape.data[x + y]);
			if (dictShape.data[count] != shape.data[pos + x * x_inc + y * y_inc]) {
				return false;
			}
			count++;
			x += 1;
			// if (x >= end || x < 0) {
			// 	x = pos % shape.sWidth;
			// 	y += y_inc;
			// }
			if (count % divider == 0) {
				x = 0;
				y += 1;
			}
		}
		return true;
	}
}

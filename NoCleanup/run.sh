#!/bin/bash
javac *.java

java PetriDish testFile/input-1.txt
diff output.txt testFile/output-1.txt > result.txt

java PetriDish testFile/input-2.txt
diff output.txt testFile/output-2.txt >> result.txt

java PetriDish testFile/input-3.txt
diff output.txt testFile/output-3.txt >> result.txt

java PetriDish testFile/input-4.txt
diff output.txt testFile/output-5.txt >> result.txt

java PetriDish $1 $2

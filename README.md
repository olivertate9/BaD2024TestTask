# Statistics of a File

This project is a Java application that provides statistics of a file containing numbers. The application reads the
numbers from the file and calculates various statistics such as max, min, median, average and longest
increasing/decreasing sequences.By default, it includes the 10m.txt and operates on that file. But you can replace it
with your own file containing numbers.

## Requirements:

1. Installed JDK
2. Installed Maven

### To run this program by default with 10m.txt:

1. Go to project folder and open terminal here.
2. Perform the following command to create jar file: mvn clean install
3. Go to target directory with: cd target
4. Run the program: java -jar Statistics-1.0.jar 10m.txt

### To run this program by default with your own txt file:

1. Go to project folder and replace 10m.txt with your yourFile.txt
2. Open terminal here.
3. Perform the following command to create jar file: mvn clean package -Dcustom.fileName=yourFileName.txt
4. Go to target directory with: cd target
5. Run the program: java -jar Statistics-1.0.jar yourFileName.txt

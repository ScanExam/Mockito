java -jar target/scanexam-0.1.0-SNAPSHOT-shaded.jar scanexam-0.1.0-SNAPSHOT-shaded.jar examples/sample.xmi# What is scanexam

ScanExam is a tool to help grading scanned paper exams. 

It allows for fast navigation among examination papers and quick grading through predefined grades.


Beware this is an very preliminary version.  

# How to compile/install ?

```
cd fr.istic.tools.scanexam
mvn clean compile assembly:single
java -jar target/scanexam-0.1.0-SNAPSHOT-jar-with-dependencies.jar examples/sample.xmi
``` 

# What else is there in the repository ?

There is a PoC for a graphical question selection tool in fr.isitc.tools.scanexam.cropping

An example of PDF geen 
 
 
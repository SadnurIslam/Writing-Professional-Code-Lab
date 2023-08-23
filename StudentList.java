
import java.io.*;
import java.text.*;
import java.util.*;
public class StudentList {
    public static String line = "";
    public static String[] students = new String[]{""};

    //reader method read the students.txt file and assign the 1st line to line string
    public static void reader(){
        try {
            BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream(Constatnts.FILE_NAME)));
            line = bufferedReader.readLine();
            students = line.split(Constatnts.SPLIT);
        }
        catch (Exception e){
            System.out.println(e);
        }
    }

    //writer method to write in students.txt file
    public static void writer(String newStudent, String lastDate, String line){
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(
                    new FileWriter(Constatnts.FILE_NAME));
            bufferedWriter.write(line+Constatnts.SPLIT+newStudent+Constatnts.LIST_UPDATE_MESSAGE+lastDate);
            bufferedWriter.close();
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void main(String[] args) {
        //When arguments length is zero it will print invalid and return immediately
        if(args[0].length()==0){
            System.out.println(Constatnts.INVALID_MESSAGE);
            return;
        }
        //when argument equals to "a" then print all student name in newline
        else if(args[0].equals(Constatnts.ALL)) {
            System.out.println(Constatnts.LOADING_MESSAGE);
            reader();
            for (String student: students){
                System.out.println(student);
            }
            System.out.println(Constatnts.LOADED_MESSAGE);
        }
        // if argument is "r" then print the name of a random student
        else if(args[0].equals(Constatnts.RANDOM)) {
            System.out.println(Constatnts.LOADING_MESSAGE);
            reader();
            int index = new Random().nextInt(students.length);
            System.out.println(students[index]);
            System.out.println(Constatnts.LOADED_MESSAGE);
        }
        //when argument is + add a new student name after the last last student
        else if(args[0].contains("+")){
            System.out.println(Constatnts.LOADING_MESSAGE);
            String newStudent = args[0].substring(1);
            String format = Constatnts.DATE_FORMAT;
            String lastDate= new SimpleDateFormat(format).format(new Date());
            reader();
            writer(newStudent,lastDate,line);
            System.out.println(Constatnts.LOADED_MESSAGE);
        }
        //when ? print whether it exists
        else if(args[0].contains(Constatnts.QUERY)) {
            System.out.println(Constatnts.LOADING_MESSAGE);
            reader();
            String student = args[0].substring(1);
            for(int index = 0; index<students.length; index++) {
                if(students[index].equals(student)) {
                    System.out.println(Constatnts.FOUND);
                    break;
                }
            }
            System.out.println(Constatnts.LOADED_MESSAGE);
        }
        // if c then count the number of students
        else if(args[0].contains(Constatnts.COUNT)) {
            System.out.println(Constatnts.LOADING_MESSAGE);
            reader();
            char alphabet[] = line.toCharArray();
            int count=1;
            for(char c:alphabet) {
                if(c ==' ') {
                    count = count + 1;
                }
            }
            System.out.println(count +Constatnts.WORDS+ alphabet.length);
            System.out.println(Constatnts.LOADED_MESSAGE);
        }
        // when doesn't match just print a message
        else{
            System.out.println(Constatnts.INVALID_MESSAGE);
        }
    }
}
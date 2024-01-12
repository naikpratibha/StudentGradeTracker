package StudentGradeTracker;
import java.util.*;

class StudentGradeTracker{
    List<student> list = new LinkedList<>();        // students data
    static HashMap<String,student> hmap = new HashMap<>(); // maps student rollno -> student information
    float classavg = 0;                             //stores class average
    float[] markavg = new float[5];;                //class average of student marks
    float[] mx = new float[5];                     
    float[] mn = new float[5];
    static String[] mxmark = new String[5];
    String[] mnmark = new String[5];
    StudentGradeTracker(){
        for(int i=0; i<5; i++){
         mx[i] = Float.MIN_VALUE;
        }
        for(int i=0; i<5; i++){
         mn[i] = Float.MAX_VALUE;
        }
    }
    void StudentInput(String rollno,String name,float[] mark){   // add data to the list and hash table
        student newstudent = new student(rollno,name,mark);
        list.add(newstudent);
        hmap.put(rollno,newstudent);
    }
    void ComputeAvg(){                                           
         int nofstudents = list.size();
         for(int i=0; i<nofstudents; i++){
            markavg[0] += list.get(i).mark[0];
            markavg[1] += list.get(i).mark[1];
            markavg[2] += list.get(i).mark[2];
            markavg[3] += list.get(i).mark[3];
            markavg[4] += list.get(i).mark[4];
            if(list.get(i).mark[0]>mx[0]){
               mxmark[0] = list.get(i).name;
               mx[0] = list.get(i).mark[0];
            }
            if(list.get(i).mark[1]>mx[1]){
               mxmark[1] = list.get(i).name;
               mx[1] = list.get(i).mark[1];
            }
            if(list.get(i).mark[2]>mx[2]){
               mxmark[2] = list.get(i).name;
               mx[2] = list.get(i).mark[2];
            }
            if(list.get(i).mark[3]>mx[3]){
               mxmark[3] = list.get(i).name;
               mx[3] = list.get(i).mark[3];
            }
            if(list.get(i).mark[4]>mx[4]){
               mxmark[4] = list.get(i).name;
               mx[4] = list.get(i).mark[4];
            }
            if(list.get(i).mark[0]<mn[0]){
               mnmark[0] = list.get(i).name;
               mx[0] = list.get(i).mark[0];
            }
            if(list.get(i).mark[1]<mn[1]){
               mnmark[1] = list.get(i).name;
               mn[1] = list.get(i).mark[1];
            }
            if(list.get(i).mark[2]<mn[2]){
               mnmark[2] = list.get(i).name;
               mn[2] = list.get(i).mark[2];
            }
            if(list.get(i).mark[3]<mn[3]){
               mnmark[3] = list.get(i).name;
               mn[3] = list.get(i).mark[3];
            }
            if(list.get(i).mark[4]<mn[4]){
               mnmark[4] = list.get(i).name;
               mn[4] = list.get(i).mark[4];
            }
            classavg += list.get(i).stuavg;
         }
         markavg[0] /= nofstudents;
         markavg[1] /= nofstudents;
         markavg[2] /= nofstudents;
         markavg[3] /= nofstudents;
         markavg[4] /= nofstudents;
         classavg /= nofstudents;
    }
    static String findSub(int idx){
      switch(idx){
         case 0 : return "Physcis";
         case 1 : return "Chemistry";
         case 2 : return "Mathematics";
         case 3 : return "Computer Science";
         default : return "English";
      }
    }
    public class student{
        String rollno;
        String name;
        float[] mark = new float[5];    // 0.physics  1.chemistry  2.mathematics  3.cs  4.english
        float stuavg = 0;
        int higscoresub = -1;
        float mx = Float.MIN_VALUE;
        int sum = 0;
        student(String rollno,String name,float[] mark){
            this.rollno = rollno;
            this.name = name;
            for(int i=0; i<5; i++){
                this.mark[i] = mark[i];
                this.stuavg += mark[i];
                this.sum += mark[i];
            }
            this.stuavg = this.stuavg/5;
            for(int i=0; i<5; i++){
                if(mark[i]>mx){
                    mx = mark[i];
                    higscoresub = i;
                }
            }
        }
      }
      public static void main(String[] args){
        try (Scanner scn = new Scanner(System.in)) {
           StudentGradeTracker student = new StudentGradeTracker();
           System.out.println("STUDENT GRADES TRACKER : ");
           System.out.print("Enter number of records for the students : ");
           int studentcount = scn.nextInt();
           for(int i=0; i<studentcount; i++){ 
           System.out.print("Roll No. : ");
           String rollno = scn.next();
           System.out.print("Name : ");
           String name = scn.next();
           float[] mark = new float[5];
           System.out.println("Enter Marks : ");
           for(int j=0; j<5; j++){
               if(j==0) System.out.print("Physics : ");
               else if(j==1) System.out.print("Chemistry : ");
               else if(j==2) System.out.print("Mathematics : ");
               else if(j==3) System.out.print("Computer Science : ");
               else if(j==4) System.out.print("English : ");
               mark[j] = scn.nextFloat();
           }
           System.out.println();
           student.StudentInput(name,rollno,mark);
           }
           student.ComputeAvg();
           //To calculate class Average.
           System.out.println("Class Average is "+student.classavg);
           //To calculate student with maximum mark for different subjects
           System.out.println("Student with maximum marks in physics is "+StudentGradeTracker.mxmark[0]);
           System.out.println("Student with maximum marks in chemistry is "+StudentGradeTracker.mxmark[1]);
           System.out.println("Student with maximum marks in Mathematics is "+StudentGradeTracker.mxmark[2]);
           System.out.println("Student with maximum marks in Computer Science is "+StudentGradeTracker.mxmark[3]);
           System.out.println("Student with maximum marks in English is "+StudentGradeTracker.mxmark[4]);
           //To find maximum scoring subject for a given Roll No.
           System.out.print("Enter roll no of the student for finding maximum scoring subject : ");
           String num = scn.next();
           System.out.println("Maximum scoring subject for rollno "+num+" is "+StudentGradeTracker.findSub(StudentGradeTracker.hmap.get(num).higscoresub));
           //To calculate average score for a given student Roll No.
           System.out.print("Enter roll no of the student for finding average scoring sbuject : ");
           num = scn.next();
           System.out.print("Average score for rollno "+num+" is "+StudentGradeTracker.hmap.get(num).stuavg);
      }
   }
}
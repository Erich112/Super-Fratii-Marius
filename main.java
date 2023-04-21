import java.util.*;
import java.io.*;

class Student implements Comparable<Student>{
    protected String name;
    protected int age;
    protected int grade;
    Student(){
        name = "N/A";
        age = 0;
        grade = 0;
    }
    Student(String n, int a, int g){
        name = n;
        age = a;
        grade = g;
    }
    Student(Student s){
        this.age = s.age;
        this.name = s.name;
        this.grade = s.grade;
    }
    String GetName(){
        return name;
    }
    int GetAge(){
        return age;
    }
    int GetGrade(){
        return grade;
    }
    @Override
    public String toString(){
        String s = "";
        s = s + name + " " + age + " " + grade;
        return s;
    }
    public Student clone(){
        Student s = new Student();
        s.age = this.GetAge();
        s.name = this.GetName();
        s.grade = this.GetGrade();
        return s;
    }
    public int compareTo(Student o){
        return (name.compareTo(o.name));
    }

}

interface Consultant {
    void ExecuteOrder66(Student[] v);

}

class ConsultantOrderByGrade implements Consultant{
    @Override
    public void ExecuteOrder66(Student[] v){
        Student aux;
        for (int i=0;i<v.length;i++){
            for(int j=i;j<v.length-1;j++)
            {
                if(v[i].GetGrade() > v[j].GetGrade()){
                    aux = v[i];
                    v[i] = v[j];
                    v[j] = aux;
                }
            }
        }
    }
}

class ConsultantOrderByName implements Consultant{
    @Override
    public void ExecuteOrder66(Student[] v){
        Student aux;
        for (int i=0;i<v.length;i++){
            for(int j=i;j<v.length-1;j++)
            {
                if(v[i].compareTo(v[j])>0){
                    aux = v[i];
                    v[i] = v[j];
                    v[j] = aux;
                }

            }
        }
    }
}

class ConsultantOrderByAge implements Consultant{
    @Override
    public void ExecuteOrder66(Student[] v){
        Student aux;
        for (int i=0;i<v.length;i++){
            for(int j=i;j<v.length-1;j++)
            {
                if(v[i].GetAge() > v[j].GetAge()){
                    aux = v[i];
                    v[i] = v[j];
                    v[j] = aux;
                }
            }
        }
    }
}

class ConsultantPrint implements Consultant{
    @Override
    public void ExecuteOrder66(Student[] v){
        for (int i=0; i<v.length; i++){
            System.out.println(v[i]);
        }
    }
}
class StudentClass extends Student{
    private Student[] v;
    private int n;
    private final int N = 10;
    Consultant c;
    StudentClass(){
        v = new Student[N];
        n = 0;
    }
    StudentClass(int n){
        v = new Student[n];
        this.n = 0;
    }

    void Add(Consultant c){
        this.c = c;
    }
    void Add(Student s){
        if (n+1>N){
            System.out.println("nu se poate adauga");
        }
        else{
            v[n] = s;
            n++;
        }
    }
    void ExecuteOrder(){
        c.ExecuteOrder66(v);
    }
}

class Main {
    public static void main(String[] args){
        Student s1,s2,s3,s4,s5,s6,s7;
        s1 = new Student();
        s2 = new Student("ana",21,5);
        s3 = new Student(s2);
        s4 = new Student("maria",20,7);
        StudentClass sc = new StudentClass();
        sc.Add(s1);
        sc.Add(s2);
        sc.Add(s3);
        sc.Add(s4);

        Consultant c1 = new ConsultantPrint();
        Consultant c2 = new ConsultantOrderByName();
        sc.Add(c2);
        sc.ExecuteOrder();
        //cica am exception ca imi intra in null dar sincer CHIAR NU STIU DE CE :,,(((
        sc.Add(c1);
        sc.ExecuteOrder();
    }
}

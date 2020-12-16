package heap;

import java.util.PriorityQueue;

public class JavaPQ {

    public static void main(String[] args) {
        PriorityQueue<Integer> pq = new PriorityQueue();
        pq.add(5);
        pq.add(15);
        pq.add(25);
        pq.add(10);
        pq.add(1);
        System.out.println("pq= "+pq.toString());
        System.out.println("pq.size()= " + pq.size());
        int size = pq.size();
        for (int i = 0; i<size ;i++) {
            System.out.println(pq.remove());
        }
        PriorityQueue<Student> pq2= new PriorityQueue<>();
        pq2.add(new Student("Ece",101,3.0f));
        pq2.add(new Student("Kerem",201,2.5f));
        pq2.add(new Student("Aslı",124,1.8f));
        pq2.add(new Student("Ahmet",267,3.02f));
        System.out.println("pq2= " + pq2.toString());
        int n= pq2.size();
        for (int i = 0; i<n ;i++) {
            System.out.println(pq2.remove());
        }
    }
}

class Student implements Comparable<Student>{
    float gpa;
    int idNo;
    String name;

    public Student(String name, int idNo,float gpa ) {
        this.gpa = gpa;
        this.idNo = idNo;
        this.name = name;
    }

    @Override
    public int compareTo(Student o) {
        if(this.gpa - o.gpa> 0){
            return 1;
        }else if(this.gpa - o.gpa < 0){
            return -1;
        }else{
            return 0;
        }
    }

    @Override
    public String toString() {
        return "Student{" +
                "Gpa=" + gpa +
                ", IdNo=" + idNo +
                ", Name='" + name + '\'' +
                '}';
    }
}
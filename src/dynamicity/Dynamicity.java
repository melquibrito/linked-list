package dynamicity;

import linkedlist.LinkedList;

public class Dynamicity {

    public static void main(String[] args) {
        LinkedList<Student> list = new LinkedList();
        Student bob = new Student(102, "Bob");
        Student adam = new Student(101, "Adam");
        Student den = new Student(103, "Den");
        Student aaron = new Student(100, "Aaron");
        Student edward = new Student(104, "Edward");
        
        list.add(den);
        list.add(adam);
        list.add(aaron);
        list.addToTheBeginning(bob);
        list.add(edward);
        list.addPreventingRepetition(bob);//Not added once it is already in the list
        
        System.out.println("As added >>");
        for(int i = 0; i < list.size(); i++) {
            list.get(i).showData();
        }
        System.out.println("***\n");
        
        list.sort();
        
        System.out.println("Sorted >>");
        for(int i = 0; i < list.size(); i++) {
            list.get(i).showData();
        }
        System.out.println("***\n");
        
        LinkedList<Integer> intList = new LinkedList();
        intList.add(1);
        intList.add(8);
        intList.addToTheBeginning(-5);
        intList.add(3);
        intList.set(0, 0);
        intList.addNeatly(-5);//Adds "-5" to the list and sorts it.
        
        
        System.out.println("Integer List sorted by method .addNeatly >>");
        for(int i = 0; i < intList.size(); i++) {
            System.out.println(intList.get(i));
        }
        System.out.println("***\n");
    }
    
}

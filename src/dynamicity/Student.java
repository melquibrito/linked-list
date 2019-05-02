package dynamicity;

public class Student implements Comparable {

    private int registrationNumber;
    private String name;

    public Student(int rn, String name) {
        this.registrationNumber = rn;
        this.name = name;
    }
    
    public void showData() {
        System.out.printf("%d %20s\n", getRegistrationNumber(), getName());
    }

    public int getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(int rn) {
        this.registrationNumber = rn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int compareTo(Object o) {
        Student x = (Student)o;
        /*Comparing by registrationNumber*/
        //return Integer.compare(this.registrationNumber, x.registrationNumber);
        /*...*/
        
        /*Comparing by name*/
        int c = this.name.compareToIgnoreCase(x.getName());
        if(c > 0) {
            return 1;
        }else if(c < 0) {
            return -1;
        }else {
            return 0;
        }
        /*...*/
    }
    
    @Override
    public String toString() {
        //return name;        
        return Integer.toString(registrationNumber);
    }

}

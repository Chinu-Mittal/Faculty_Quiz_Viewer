package user;

public class student extends User {
    public student(String name, String stream) {
        super(name, stream);
    }

    // Method Overloading for personalized greeting
    public void displayInfo(String greeting) {
        System.out.println(greeting + ", " + name + "!");
        displayInfo();
    }
}

package user;

public class User {
    String name;
    String stream;

    public User(String name, String stream) {
        this.name = name;
        this.stream = stream;
    }

    public void displayInfo() {
        System.out.println("Name: " + name);
        System.out.println("Stream: " + stream);
    }
}

package ro.ubbcluj.map.domain;

public class User extends Entity<Long>{
    String firstName;
    String lastName;

    public User(long id, String firstName, String lastName) {
        super(id);
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    @Override
    public String toString(){
        return "User{" +
                "id=" + id + "; " +
                "firstName=" + firstName + "; " +
                "lastName=" + lastName +
                '}';
    }
}

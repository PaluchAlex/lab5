package ro.ubbcluj.map.ui;

import ro.ubbcluj.map.domain.Friendship;
import ro.ubbcluj.map.domain.User;
import ro.ubbcluj.map.domain.validators.ValidationException;
import ro.ubbcluj.map.repository.RepositoryException;
import ro.ubbcluj.map.service.NetworkService;
import ro.ubbcluj.map.service.UserService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class UserInterface {
    private final UserService userService;
    private final NetworkService networkService;

    public UserInterface(UserService userService, NetworkService networkService){
        this.userService = userService;
        this.networkService = networkService;
    }

    public void run(){
        while(true){
            try {
                displayMenu();
                int option;

                System.out.println("\n");
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                System.out.print("Enter option: ");
                option = Integer.parseInt(reader.readLine());

                switch (option) {
                    case 1 -> handleAddUser();
                    case 2 -> handleDeleteUser();
                    case 3 -> handleUpdateUser();
                    case 4 -> handleAddFriendship();
                    case 5 -> handleDeleteFriendship();
                    case 6 -> getUserByID();
                    case 7 -> handleShowAllUsers();
                    case 8 -> getFriendshipByID();
                    case 9 -> getAllFriendships();
                    case 0 -> {
                        return;
                    }
                    default -> System.out.println("\nWrong option, try again!\n");
                }
            }
            catch (IOException | ValidationException | RepositoryException | NumberFormatException e){
                System.out.println(e.getMessage());
            }
        }
    }

    private void displayMenu(){
        System.out.println("1. Add user");
        System.out.println("2. Delete user");
        System.out.println("3. Modify user");
        System.out.println("4. Add friendship");
        System.out.println("5. Delete friendship");
        System.out.println("6. Get user by ID");
        System.out.println("7. Show all users");
        System.out.println("8. Get friedship by ID");
        System.out.println("9. Show all friendships");
        System.out.println("0. Exit");
    }

    private void handleAddUser() throws IOException {
        User u = getUserInput();
        userService.addEntity(u);
    }

    private User getUserInput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("First Name: ");
        String firstName = reader.readLine();

        System.out.println("Last Name: ");
        String lastName = reader.readLine();

        System.out.println("ID: ");
        long id = Long.parseLong(reader.readLine());

        return new User(id, firstName, lastName);
    }

    private Long readId() throws IOException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("ID: ");
        return Long.parseLong(reader.readLine());
    }

    private void handleDeleteUser() throws IOException {
        userService.deleteEntity(readId());
    }

    private void handleUpdateUser() throws IOException {
        User u = getUserInput();
        userService.updateEntity(u);
    }

    private void handleAddFriendship() throws IOException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("User ID 1: ");
        Long id1 = Long.parseLong(reader.readLine());

        System.out.println("User ID 2: ");
        Long id2 = Long.parseLong(reader.readLine());

        System.out.println("Friendship ID:");
        long id = Long.parseLong(reader.readLine());

        Friendship f = new Friendship(id, id1, id2);
        networkService.addEntity(f);
    }

    private void handleDeleteFriendship() throws IOException{
        networkService.deleteEntity(readId());
    }

    private void handleShowAllUsers(){
        Iterable<User> all = userService.getAll();
        for(User u: all) System.out.println(u);
    }

    private void getUserByID() throws IOException {
        User u = userService.get(readId());
        System.out.println(u);
    }

    private void getAllFriendships() {
        Iterable<Friendship> all = networkService.getAll();
        for(Friendship f: all) System.out.println(f);
    }

    private void getFriendshipByID() throws IOException {
        Friendship f = networkService.get(readId());
        System.out.println(f);
    }
}

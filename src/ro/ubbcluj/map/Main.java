package ro.ubbcluj.map;

import ro.ubbcluj.map.domain.validators.FriendshipValidator;
import ro.ubbcluj.map.domain.validators.UserValidator;
import ro.ubbcluj.map.repository.SQLUserRepository;
import ro.ubbcluj.map.service.NetworkService;
import ro.ubbcluj.map.service.UserService;
import ro.ubbcluj.map.ui.UserInterface;
import ro.ubbcluj.map.repository.SQLFriendshipRepository;

public class Main {

    public static void main(String[] args){
        String url = "jdbc:postgresql://localhost:5432/Lab5";
        String username = "postgres";
        String password = "12345678";

        UserValidator userValidator = new UserValidator();
        SQLUserRepository userRepo = new SQLUserRepository(userValidator,url,username,password);

        FriendshipValidator friendshipValidator = new FriendshipValidator();
//        NetworkFileRepository networkFileRepository = new NetworkFileRepository(friendshipValidator, networkFileName);
        SQLFriendshipRepository friendshipRepo = new SQLFriendshipRepository(friendshipValidator, url, username, password);

        UserService userService = new UserService(userRepo);
        NetworkService networkService = new NetworkService(friendshipRepo);

        UserInterface ui = new UserInterface(userService, networkService);

        ui.run();
    }
}

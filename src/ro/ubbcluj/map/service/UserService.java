package ro.ubbcluj.map.service;
import ro.ubbcluj.map.repository.Repository;
import ro.ubbcluj.map.domain.User;

public class UserService extends AbstractService<Long, User>{

    public UserService(Repository<Long, User> repo) {
        super(repo);
    }

}

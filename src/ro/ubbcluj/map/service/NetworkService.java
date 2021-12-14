package ro.ubbcluj.map.service;

import ro.ubbcluj.map.domain.Friendship;
import ro.ubbcluj.map.repository.Repository;

public class NetworkService extends AbstractService<Long, Friendship>{
    public NetworkService(Repository<Long, Friendship> repo) {
        super(repo);
    }
}

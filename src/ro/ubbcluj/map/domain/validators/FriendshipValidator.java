package ro.ubbcluj.map.domain.validators;

import ro.ubbcluj.map.domain.Friendship;
import ro.ubbcluj.map.domain.User;

public class FriendshipValidator implements Validator<Friendship>{

    @Override
    public void validate(Friendship friendship) throws ValidationException {
        if(friendship.getUserId1() == null)
            throw new ValidationException("Id1 can't be null");
        if(friendship.getUserId2() == null)
            throw new ValidationException("Id2 can't be null");
        if(friendship.getUserId1().equals(friendship.getUserId2()))
            throw new ValidationException("Id1 can't be the same as Id2!");
    }

}

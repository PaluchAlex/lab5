package ro.ubbcluj.map.domain.validators;

import ro.ubbcluj.map.domain.User;

public class UserValidator implements Validator<User> {

    public static final String EMPTY = "";

    @Override
    public void validate(User user) throws ValidationException {
        if (user.getFirstName() == null)
            throw new ValidationException("First name cannot be null!");
        if (user.getFirstName().equals(EMPTY))
            throw new ValidationException("First name cannot be left blank");
        if (user.getLastName() == null)
            throw new ValidationException("Last name cannot be null!");
        if (user.getLastName().equals(EMPTY))
            throw new ValidationException("Last name cannot be left blank");

    }

}

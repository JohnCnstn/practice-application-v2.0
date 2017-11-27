package classes.data.service;

import classes.data.entity.User;

public interface UserService {
    User getByUserName(String userName);
}

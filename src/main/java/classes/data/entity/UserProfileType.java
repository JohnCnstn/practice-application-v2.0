package classes.data.entity;

import lombok.Getter;

public enum UserProfileType {

    STUDENT("STUDENT"),
    HEAD_MASTER("HEAD_MASTER"),
    ADMIN("ADMIN");

    @Getter
    String userProfileType;

    UserProfileType(String userProfileType) {
        this.userProfileType = userProfileType;
    }

}

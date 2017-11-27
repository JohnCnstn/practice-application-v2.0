package classes.data.entity;

import lombok.Getter;

public enum StudentProfileType {

    ON_PRACTICE("ON_PRACTICE"),
    AVAILABLE("AVAILABLE");

    @Getter
    String userProfileType;

    StudentProfileType(String userProfileType) {
        this.userProfileType = userProfileType;
    }
}

package com.leguia.phonebooktuco;

public enum AddContactValidationError {
    FIRSTNAME_EMPTY("Firstname is required"),
    LASTNAME_EMPTY("Lastname is required"),
    PHONE_NUMBER_EMPTY("Phone cannot be empty"),
    NO_ERROR("");

    private String message;

    AddContactValidationError(String message){
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}

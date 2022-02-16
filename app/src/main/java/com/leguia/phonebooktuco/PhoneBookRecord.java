package com.leguia.phonebooktuco;

public class PhoneBookRecord {
    private String pNumber;
    private String firstName;
    private String lastName;

    public PhoneBookRecord(String firstName, String lastName, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.pNumber = phoneNumber;

    }

    public String getNumber() {
        return pNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public boolean match(String text){
        return firstName.contains(text) ||
                lastName.contains(text) ||
                pNumber.contains(text);
    }
}



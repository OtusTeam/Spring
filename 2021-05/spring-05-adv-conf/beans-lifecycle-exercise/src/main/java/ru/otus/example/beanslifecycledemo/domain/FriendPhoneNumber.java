package ru.otus.example.beanslifecycledemo.domain;

public class FriendPhoneNumber extends PhoneNumber {
    @Override
    public String getOwnerName() {
        return "Друг";
    }
}

package ru.mycash.cash;



public class AuthorizedUser {

private static int id;

    public static void setId(int id) {
        AuthorizedUser.id = id;
    }

    public static int id() {
        return id;
    }

}
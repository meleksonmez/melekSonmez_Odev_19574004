package com.example.application;

import java.util.ArrayList;

public class Users {
    private String username;
    private String password;
    private int image;

    public Users(){}

    public Users (String username, String password){
        this.username = username;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public static ArrayList<Users> getUserList(){
        ArrayList<Users> userList = new ArrayList<>();
        int userImages[] = {
                R.drawable.im_1, R.drawable.im_2, R.drawable.im_3, R.drawable.im_4, R.drawable.im_5,
                R.drawable.im_6, R.drawable.im_7, R.drawable.im_8, R.drawable.im_9, R.drawable.im_10,
                R.drawable.im_11,  R.drawable.im_12, R.drawable.im_13, R.drawable.im_14, R.drawable.im_15,
                R.drawable.im_16, R.drawable.im_17, R.drawable.im_18, R.drawable.im_19, R.drawable.im_20
        };
        Users[] users = {
                new Users("Melek", "1"), new Users("Sönmez", "2"), new Users("Ece", "3"), new Users("Ayça", "4"),
                new Users("Mehmet", "5"), new Users("Selma", "6"), new Users("Tuba", "7"), new Users("Ayşegül", "8"),
                new Users("Leyla", "9"), new Users("Selin", "10"), new Users("Hakan", "11"), new Users("Mert", "12"),
                new Users("Alp", "13"), new Users("Eren", "14"), new Users("Cem", "15"), new Users("Mustafa", "16"),
                new Users("Yuşa", "17"), new Users("Can", "18"), new Users("Mehmet", "19"), new Users("Ahmet", "20"),
        };

        for (int i = 0; i < userImages.length; i++) {
            Users temp = new Users();
            temp.setUsername(users[i].getUsername());
            temp.setPassword(users[i].getPassword());
            temp.setImage(userImages[i]);

            userList.add(temp);
        }

        return userList;
    }

}























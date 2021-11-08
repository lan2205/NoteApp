package com.example.noteapp.User;

import java.io.Serializable;

public class User implements Serializable{
    private int id;
    private String full_name;
    private String username;
    private String pass_word;
    private String email;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPass_word() {
        return pass_word;
    }

    public void setPass_word(String pass_word) {
        this.pass_word = pass_word;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", full_name='" + full_name + '\'' +
                ", username='" + username + '\'' +
                ", pass_word='" + pass_word + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}

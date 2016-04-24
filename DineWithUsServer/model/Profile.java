package model;

import java.util.ArrayList;

/**
 * Created by HuiJun on 4/14/16.
 */
public class Profile {
    private String firstname, lastname;
    private ArrayList<String> Likes, Dislikes;
    private String phone, email;
    private int age;

    public Profile() {
        this.firstname = "Niteesh";
        this.lastname = "Sundaram";
        this.Likes = new ArrayList<String>();
        this.Dislikes = new ArrayList<String>();
        this.phone = "(412) 303-0054";
        this.email = "nsundara@andrew.cmu.edu";
        this.age = 22;
    }

    public void setFirstname(String name) {
        this.firstname = name;
    }

    public String getFirstname() {
        return this.firstname;
    }

    public void setLastname(String name) {
        this.lastname = name;
    }

    public String getLastname() {
        return this.lastname;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return this.email;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getAge() {
        return this.age;
    }

    public void addLike(String like) {
        this.Likes.add(like);
    }

    public ArrayList<String> getLikes() {
        return this.Likes;
    }

    public void addDislike(String dislike) {
        this.Dislikes.add(dislike);
    }

    public void removeDislike(String dislike) {
        this.Dislikes.remove(dislike);
    }
    
    public void removeLike(String like) {
        this.Likes.add(like);
    }

    public ArrayList<String> getDislikes() {
        return this.Dislikes;
    }
}

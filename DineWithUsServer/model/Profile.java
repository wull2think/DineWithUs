package model;

/**
 * Created by HuiJun on 4/14/16.
 */
public class Profile {
    private String firstname, lastname;
    private String [] Likes, Dislikes;
    private String phone, email;
    private int age;

    public Profile() {
        this.firstname = "Niteesh";
        this.lastname = "Sundaram";
        this.Likes = new String[3];
        this.Dislikes = new String[3];
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

    public void setLikes(String like, int i) {
        this.Likes[i] = like;
    }

    public String [] getLikes() {
        return this.Likes;
    }

    public void setDislikes(String dislike, int i) {
        this.Dislikes[i] = dislike;
    }

    public String [] getDislikes() {
        return this.Dislikes;
    }
}

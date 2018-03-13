package models;

public class Actor {
    private String text;
    private User user;

    public Actor() {
    }

    public Actor(String text) {
        this.text = text;
    }

    public Actor(String text, User user) {
        this.text = text;
        this.user = user;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

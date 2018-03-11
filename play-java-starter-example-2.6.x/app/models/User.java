package models;

import java.util.List;

public class User {
    private List<String> texts;
    private final String id;
    private final String name;
    private final String screen_name;
    private final String description;
    private final String profile_image_url;
    private final int followers;
    private final int posts;
    private final int friends;
    private final String created_at;


    public User(String id, String name, String screen_name, String description, String profile_image_url, int followers, int posts, int friends, String created_at) {
        this.id = id;
        this.name = name;
        this.screen_name = screen_name;
        this.description = description;
        this.profile_image_url = profile_image_url;
        this.followers = followers;
        this.posts = posts;
        this.friends = friends;
        this.created_at = created_at;
    }

    public List<String> getTexts() {
        return texts;
    }

    public void setTexts(List<String> texts) {
        this.texts = texts;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getProfile_image_url() {
        return profile_image_url;
    }

    public String getScreen_name() {
        return screen_name;
    }

    public int getFollowers() {
        return followers;
    }

    public int getPosts() {
        return posts;
    }

    public int getFriends() {
        return friends;
    }

    public String getCreated_at() {
        String[] times = this.created_at.split(" ");
        String format = times[0] + " " + times[1] + " "  + times[2] + " " + times[5];
        return format;
    }
}

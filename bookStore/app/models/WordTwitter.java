package models;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WordTwitter {
    public String name;
    public String text;
    public String id;
    public static List<WordTwitter> twitters = new ArrayList<>();

    public WordTwitter(){}

    public WordTwitter(String name, String text, String id){
        this.id = id;
        this.name = name;
        this.text = text;
    }


    public static List<WordTwitter> alltweets(){
        return twitters;
    }

    public static void add(WordTwitter wordTwitter){
        twitters.add(wordTwitter);
    }

    public static boolean remove(WordTwitter wordTwitter) {
        return twitters.remove(wordTwitter);
    }

}

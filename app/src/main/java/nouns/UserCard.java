package nouns;

/**
 * Created by alice on 8/6/14.
 */
public class UserCard {

    public final int number;
    public final String name;
    public final String description;
    public final String card_id;
    public final String deck_id;
    public final String id;

    public UserCard(String id, String card_id, String deck_id, String description, String name, int number){
        this.id = id;
        this.card_id = card_id;
        this.deck_id = deck_id;
        this.description = description;
        this.name = name;
        this.number = number;
    }

    public String imageURL(){
        return String.format("/user_cards?card_id=%s&deck_id=%s&uc=%s", card_id, deck_id, id);
    }

    public String imageURL(int size){
        return String.format("/user_cards?card_id=%s&deck_id=%s&uc=%s&size=%d", card_id, deck_id, id, size);
    }
}

package nouns;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by alice on 11/4/2014.
 */
public class MyDeck extends DBNoun {

    public MyDeck(Connection conn, String deck_id, Date start_date, Date end_date){
        super(conn);

        try {
           Statement statement =  connection.createStatement();
           String createString = "INSERT INTO Deck(`deck_id`,`start_date`,`end_date`) VALUES(?, ?, ?)";

           dbId = 1;
        } catch (SQLException e){

        }
    }
}

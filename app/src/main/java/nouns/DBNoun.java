package nouns;

import java.sql.Connection;
import java.sql.Statement;

/**
 * Created by alice on 11/4/2014.
 */
public class DBNoun {

    protected Connection connection;
    protected long dbId;

    public DBNoun(Connection conn){
        connection = conn;
    }

    public long getDbId(){
        return dbId;
    }

}

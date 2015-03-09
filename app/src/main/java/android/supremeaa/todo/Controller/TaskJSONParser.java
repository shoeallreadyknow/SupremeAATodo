package android.supremeaa.todo.Controller;

import org.json.JSONObject;

/**
 * Created by Student on 3/8/2015.
 */
public class TaskJSONParser {
    public static TaskJSONParser parser;

    private TaskJSONParser(){

    }

    public static TaskJSONParser getParser(){
        if(parser == null){
            parser = new TaskJSONParser();
        }
        return parser;
    }

    public JSONObject parse(){
        return null;
    }
}

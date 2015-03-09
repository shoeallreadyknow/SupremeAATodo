package android.supremeaa.todo.Controller;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by Student on 2/26/2015.
 */
public final class TaskSerializer {
    public static JSONArray parseJSONFromAsset(Context c) {
        JSONArray jsonArray = null;
        InputStream is = null;
        try {
            is = c.getAssets().open("JSON/taskdata.json");
        } catch (IOException e) {
            e.printStackTrace();
        }
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder stringBuilder = new StringBuilder();

        String string;
        try {
            while((string = reader.readLine()) !=null) stringBuilder.append(string);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            jsonArray = new JSONArray(stringBuilder.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonArray;
    }
}

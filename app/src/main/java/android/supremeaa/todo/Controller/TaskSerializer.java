package android.supremeaa.todo.Controller;

import android.content.Context;
import android.supremeaa.todo.Model.Task;
import android.supremeaa.todo.TodoActivity;
import android.util.JsonWriter;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.regex.Pattern;

/**
 * Created by Student on 2/26/2015.
 */
public final class TaskSerializer {
    public static boolean fromJSONObject(final Task task, final JSONObject jsonObject){
        String title = jsonObject.optString("title", task.getTitle());
        task.setTitle(title);
        String date = jsonObject.optString("date", task.getDate());
        task.setDate(date);
        String priority = jsonObject.optString("priority", task.getPriority());
        task.setPriority(priority);

        return true;
    }
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

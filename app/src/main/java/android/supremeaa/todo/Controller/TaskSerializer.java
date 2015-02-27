package android.supremeaa.todo.Controller;

import android.supremeaa.todo.Model.Task;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.Pattern;

/**
 * Created by Student on 2/26/2015.
 */
public final class TaskSerializer {

    public static JSONObject toJSONObject(final Task task){
        JSONObject jsonObject = new JSONObject();

        try {
            jsonObject.put("title", task.getTitle());
            jsonObject.put("date", task.getDate());
            jsonObject.put("priority", task.getPriority());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return jsonObject;
    }
    public static void writeJSONfile(JSONObject jsonObject) throws IOException{
        FileWriter file = new FileWriter("../../../assets/JSON/taskdata.txt");

        try {
            file.write(jsonObject.toString());
            System.out.println("Successfully Copied JSON Object to File...");
            System.out.println("\nJSON Object: " + jsonObject);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            file.flush();
            file.close();
        }
    }

    public static boolean fromJSONObject(final Task task, final JSONObject jsonObject){
        String title = jsonObject.optString("title", task.getTitle());
        task.setTitle(title);
        String date = jsonObject.optString("date", task.getDate());
        task.setDate(date);
        int priority = jsonObject.optInt("priority", task.getPriority());
        task.setPriority(priority);

        return true;
    }
}

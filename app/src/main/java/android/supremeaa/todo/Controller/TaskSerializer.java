package android.supremeaa.todo.Controller;

import android.content.Context;
import android.supremeaa.todo.Model.Task;
import android.supremeaa.todo.TodoActivity;
import android.util.JsonWriter;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.regex.Pattern;

/**
 * Created by Student on 2/26/2015.
 */
public final class TaskSerializer {

   /**
    * Returns a JSON object that can then be written into a file.
    * @param  task  a task object that specifies the object to be converted into JSON object
    * @return the JSON object
    * @see    android.supremeaa.todo.Model.Task
    * */
    public static JSONObject toJSONObject(final Task task){
        JSONObject jsonObject = new JSONObject();
        // puts task data into JSON object
        try {
            jsonObject.put("title", task.getTitle());
            jsonObject.put("date", task.getDate());
            jsonObject.put("priority", task.getPriority());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return jsonObject;
    }
    public static void writeJSONfile(OutputStream out) throws IOException{
        JsonWriter writer = new JsonWriter(new OutputStreamWriter(out, "UTF-8"));
        jsonFinal(writer);
    }
    public static void jsonFinal(JsonWriter writer) throws IOException{
        writer.beginObject();
        writer.name("title").value("Clean Room");
        writer.name("date").value("3-1-2015");
        writer.name("priority").value(1);
        writer.endObject();
        writer.close();
    }
    /**
     * Writes a JSONObject to a file.
     * @param  jsonObject  the JSONObject to be written into a file
     * @return returns true if data is set
     * @see    android.supremeaa.todo.Controller.TaskSerializer
     * */
    public static boolean fromJSONObject(final Task task, final JSONObject jsonObject){
        String title = jsonObject.optString("title", task.getTitle());
        task.setTitle(title);
        String date = jsonObject.optString("date", task.getDate());
        task.setDate(date);
        int priority = jsonObject.optInt("priority", task.getPriority());
        task.setPriority(priority);

        return true;
    }
    public static String loadJSONFromAsset(Context c) {
        String json = null;
        try {
            InputStream is = c.getAssets().open("taskdata.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }
}

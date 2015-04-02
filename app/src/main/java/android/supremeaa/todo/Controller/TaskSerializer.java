package android.supremeaa.todo.Controller;

import android.content.Context;
import android.supremeaa.todo.Model.Task;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Student on 2/26/2015.
 */
public class TaskSerializer {
    static class RecipeCompare implements Comparator<Task> {

        @Override
        public int compare(Task o1, Task o2) {
            // write comparison logic here like below , it's just a sample
            return o1.getPriority().compareTo(o2.getPriority());
        }
    }
    /**
     * This methood is used to parse JSONArrays from json files in the internal storage.
     * @param appContext this gets the applications context, which allows the function to access the
     *          assets folder.
     * @return JSONArray jsonArray parsed from taskdata.json file.
     */
    public static JSONArray parseJSONFromFile(Context appContext) {
        JSONArray jsonArray = null;
        InputStream is = null;

        try {
            if(appContext.openFileInput("taskdata.json") != null) {
                is = appContext.openFileInput("taskdata.json");
            } else {
                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(appContext.openFileOutput("taskdata.json", Context.MODE_PRIVATE));
                outputStreamWriter.write("");
                outputStreamWriter.close();

                return null;
            }
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
    /**
     * This methood is used to parse JSONArrays from json files in the assets folder.
     * @param appContext this gets the applications context, which allows the function to access the
     *          assets folder.
     * @return JSONArray jsonArray parsed from taskdata.json file.
     */
    public static JSONArray parseJSONFromAsset(Context appContext) {
        JSONArray jsonArray = null;
        InputStream is = null;

        try {
            is = appContext.getAssets().open("JSON/taskdata.json");
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
    /**
     * This method populates a List with Task Objects using data from a JSONArray
     * @param jsonArray gets JSONArray parsed from parseJSONFromAsset method.
     * @return taskList List of Task Objects
     * @see android.supremeaa.todo.Model.Task
     */
    public static List<Task> toTaskList(JSONArray jsonArray){
        List<Task> taskList = new ArrayList<Task>();

        try {
            for(int i = 0; i < jsonArray.length(); i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String title = jsonObject.getString("title");
                String date = jsonObject.getString("date");
                String priority = jsonObject.getString("priority");

                taskList.add(new Task(title, date, priority));

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Collections.sort(taskList, new RecipeCompare());

        return taskList;
    }
    public static JSONArray toJSONArray(List<Task> taskList){
        JSONArray jsonArray = new JSONArray();

        try {
             for(Task task : taskList){
                JSONObject jsonObject = new JSONObject();

                jsonObject.put("title", task.getTitle());
                jsonObject.put("date", task.getDate());
                jsonObject.put("priority", task.getPriority());

                jsonArray.put(jsonObject);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return jsonArray;
    }
    public static List<Task> deleteFirstTask(List<Task> taskList){
        if(taskList != null) {
            taskList.remove(0);
        }
        return taskList;
    }
    public static List<Task> deleteTask(List<Task> taskList, int position){
        if(taskList != null) {
            taskList.remove(position);
        }
        return taskList;
    }
    public static boolean saveAppJSONFile( Context appContext, final String json ){
        try{
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(appContext.openFileOutput("taskdata.json", Context.MODE_PRIVATE));
            outputStreamWriter.write(json);
            outputStreamWriter.close();

            return true;
        }
        catch(IOException e){
            e.printStackTrace();
            return false;
        }
    }
    public static boolean saveAppJSONFile( Context appContext, final JSONArray jsonArray ){
        return saveAppJSONFile( appContext, jsonArray.toString() );
    }

}

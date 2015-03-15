package android.supremeaa.todo.Controller;

import android.content.Context;
import android.supremeaa.todo.Model.Task;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Student on 2/26/2015.
 */
public class TaskSerializer {
    /**
     * This methood is used to parse JSONArrays from json files.
     * @param appContext this gets the applications context, which allows the function to access the
     *          assets folder.
     * @return JSONArray jsonArray parsed from taskdata.json file.
     */
    public static JSONArray parseJSONFromAsset(Context appContext) {
        JSONArray jsonArray = null;
        FileInputStream is = null;

        try {
            is = appContext.openFileInput("taskdata.json");;
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
            for(int i = 0; i < jsonArray.length(); i ++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String title = jsonObject.getString("title");
                String date = jsonObject.getString("date");
                String priority = jsonObject.getString("priority");

                taskList.add(new Task(title, date, priority));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return taskList;
    }
    public static JSONArray toJSONArray(List<Task> taskList){
        JSONArray jsonArray = new JSONArray();

        try {
             for(int i = 0; i < taskList.size(); i++){
                JSONObject jsonObject = new JSONObject();
                Task task = taskList.get(i);

                jsonObject.put("title", task.getTitle());
                jsonObject.put("date", task.getDate());
                jsonObject.put("priority", task.getPriority());

                jsonArray.put(i,jsonObject);
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
    public static boolean saveAppJSONFile( Context appContext, final String json ){
        try{
            final FileOutputStream fos = appContext.openFileOutput("taskdata.json",Context.MODE_PRIVATE);
            fos.write( json.getBytes() );
            fos.close();
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

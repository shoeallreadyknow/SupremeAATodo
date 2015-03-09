package android.supremeaa.todo;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.supremeaa.todo.Controller.TaskJSONParser;
import android.supremeaa.todo.Controller.TaskSerializer;
import android.supremeaa.todo.Model.Task;
import android.supremeaa.todo.Model.TaskAdapter;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


public class TodoActivity extends Activity  {

    protected TextView textView;
    public static Context context;
    public static JSONObject jsonObject;
    public static ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo);
        TodoActivity.context = getApplicationContext();
        JSONArray jsonArray = TaskSerializer.parseJSONFromAsset(TodoActivity.context);

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

        ListView listView = (ListView)findViewById(R.id.listView);
        TaskAdapter adapter = new TaskAdapter(this, R.layout.list_item, taskList);

        listView.setAdapter(adapter);
    }

}

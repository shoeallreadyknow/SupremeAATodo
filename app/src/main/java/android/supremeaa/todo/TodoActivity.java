package android.supremeaa.todo;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.supremeaa.todo.Controller.TaskSerializer;
import android.supremeaa.todo.Model.Task;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;


public class TodoActivity extends Activity  {

    protected TextView textView;
    public static Context context;
    public static JSONObject jsonObject;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo);
        TodoActivity.context = getApplicationContext();
        try {
            this.jsonObject = new JSONObject(TaskSerializer.loadJSONFromAsset(TodoActivity.context));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Task task = new Task("","",0);

        TaskSerializer.fromJSONObject(task, jsonObject);
        textView = (TextView)findViewById(R.id.textView);
        textView.setText(task.title);
        //listView = (ListView)findViewById(R.id.listView);
    }

}

package android.supremeaa.todo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.supremeaa.todo.Controller.TaskSerializer;
import android.supremeaa.todo.Model.Task;
import android.supremeaa.todo.Model.TaskAdapter;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;

import java.util.List;

/**
 * <h1>TodoApp</h1>
 * This to do App Allows users to create tasks that they must complete,
 * and assist them in completing them in the quickest most efficient way
 * possible
 * @author Philipp Faraee
 * @version 1.0
 * @since Monday, February 23, 2015 4:37:47 PM
 */
public class TodoActivity extends Activity  {
    public static Context context;
    public static ListView listView;
    private List<Task> taskList;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo);
        TodoActivity.context = getApplicationContext();
        JSONArray jsonArray = TaskSerializer.parseJSONFromAsset(TodoActivity.context);
        taskList = TaskSerializer.toTaskList(jsonArray);

        listView = (ListView)findViewById(R.id.listView);
        TaskAdapter adapter = new TaskAdapter(this, R.layout.list_item, taskList);
        listView.setAdapter(adapter);

        Button button = (Button)findViewById(R.id.trash_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TaskSerializer.deleteFirstTask(taskList);
                JSONArray saveJSONArray = TaskSerializer.toJSONArray(taskList);
                TaskSerializer.saveAppJSONFile(TodoActivity.context, saveJSONArray);
                finish();
                startActivity(getIntent());
            }
        });
        
        JSONArray saveJSONArray = TaskSerializer.toJSONArray(taskList);
        TaskSerializer.saveAppJSONFile(TodoActivity.context, saveJSONArray);
    }



}

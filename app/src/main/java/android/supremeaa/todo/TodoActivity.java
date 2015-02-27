package android.supremeaa.todo;

import android.app.Activity;
import android.os.Bundle;
import android.supremeaa.todo.Controller.TaskSerializer;
import android.supremeaa.todo.Model.Task;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import org.json.JSONObject;

import java.io.IOException;


public class TodoActivity extends Activity {

    protected ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo);

        Task task = new Task("Clean Room", "2-26-2015", 1);
        JSONObject jsonObject = TaskSerializer.toJSONObject(task);
        try {
            TaskSerializer.writeJSONfile(jsonObject);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //listView = (ListView)findViewById(R.id.listView);
    }
}

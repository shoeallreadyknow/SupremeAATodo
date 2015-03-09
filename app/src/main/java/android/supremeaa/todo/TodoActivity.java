package android.supremeaa.todo;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.supremeaa.todo.Controller.TaskSerializer;
import android.supremeaa.todo.Model.Task;
import android.supremeaa.todo.Model.TaskAdapter;
import android.widget.ListView;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class TodoActivity extends Activity  {
    public static Context context;
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

        setContentView(R.layout.activity_todo);
        String clean_room = "Clean The Room";
        String mowe_lawn = "Mow Lawn";
        String go_to_store = "Go To The Store";
        String wash_car = "Wash The Car";
        String walk_dog = "Walk The Dog";


        String[] listItems = new String[5];
        listItems[0] = clean_room;
        listItems[1] = mowe_lawn;
        listItems[2] = go_to_store;
        listItems[3] = wash_car;
        listItems[4] = walk_dog;


//        *** Original Center ListView Layout ***
//        ArrayAdapter<String> listItemAdapter = new ArrayAdapter<String>(this, R.layout.center_layout, R.id.taskName, listItems);
//        ListView lv = (ListView) this.findViewById(R.id.listView);
//        lv.setAdapter(listItemAdapter);

        listView = (ListView)findViewById(R.id.listView);
        TaskAdapter adapter = new TaskAdapter(this, R.layout.list_item, taskList);

        listView.setAdapter(adapter);
    }

}

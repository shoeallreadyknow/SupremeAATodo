package android.supremeaa.todo;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class TodoActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo);


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


        ArrayAdapter<String> listItemAdapter = new ArrayAdapter<String>(this, R.layout.center_layout, R.id.taskName, listItems);
        ListView lv = (ListView) this.findViewById(R.id.listView);
        lv.setAdapter(listItemAdapter);

    }
}
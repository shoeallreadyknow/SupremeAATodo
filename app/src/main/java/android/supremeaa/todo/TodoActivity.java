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
        String clean_room = "clean the room";
        String mowe_lawn = "mowe lawn";
        String go_to_store = "go to the store";
        String wash_car = "wash the car";
        String walk_dog = "walk the dog";


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
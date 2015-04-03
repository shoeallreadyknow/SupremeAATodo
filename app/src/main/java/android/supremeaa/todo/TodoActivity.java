package android.supremeaa.todo;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.supremeaa.todo.Controller.TaskSerializer;
import android.supremeaa.todo.Model.AnimatingRelativeLayout;
import android.supremeaa.todo.Model.Task;
import android.supremeaa.todo.Model.TaskAdapter;
import android.supremeaa.todo.Model.UpdateViewTask;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;

import java.util.Calendar;
import java.util.List;

/**
 * <h1>TodoApp</h1>
 * This to do App Allows users to create tasks that they must complete,
 * and assist them in completing them in the quickest most efficient way
 * possible
 * @author Philipp Faraee
 * @version 1.0.4
 * @since Monday, February 23, 2015 4:37:47 PM
 */
public class TodoActivity extends Activity  {
    public static Context context;
    public static ListView listView;
    private List<Task> taskList;

    private TextView display;
    private Button changeDate, newTaskButton;
    private EditText editText;

    private int year;
    private int month;
    private int day;

    public String title;
    public String date;
    public String priority;

    public static TaskAdapter adapter;

    static final int DATE_PICKER_ID = 1111;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.todo_loadout);

        Activity todoActivity = this;
        context = getApplicationContext();

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        if(!prefs.getBoolean("firstTime", false)) {
            TaskSerializer.saveAppJSONFile(context, TaskSerializer.parseJSONFromAsset(context));

            SharedPreferences.Editor editor = prefs.edit();
            editor.putBoolean("firstTime", true);
            editor.commit();
        }
        JSONArray jsonArray = TaskSerializer.parseJSONFromFile(context);
        taskList = TaskSerializer.toTaskList(jsonArray);

        editText = (EditText)findViewById(R.id.editText);
        display = (TextView) findViewById(R.id.display);
        changeDate = (Button)findViewById(R.id.setDate);
        newTaskButton = (Button)findViewById(R.id.newTaskButton);
        listView = (ListView)findViewById(R.id.listView);

        final AnimatingRelativeLayout animatingRelativeLayout = (AnimatingRelativeLayout)findViewById(R.id.newTaskLayout);
        animatingRelativeLayout.hide();

        newTaskButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(!animatingRelativeLayout.isVisible()) {
                    animatingRelativeLayout.show();
                } else if(animatingRelativeLayout.isVisible()){
                    animatingRelativeLayout.hide();
                }
            }
        });

        adapter = new TaskAdapter(this, R.layout.list_item, taskList, todoActivity);
        listView.setAdapter(adapter);

        final Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);

        display.setText(new StringBuilder().append(month + 1).append("-").append(day).append("-").append(year).append(" "));
        changeDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(DATE_PICKER_ID);
            }
        });

        Button addTaskButton = (Button)findViewById(R.id.addTask);
        addTaskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                title = editText.getText().toString();
                date = new StringBuilder().append(month + 1).append("-").append(day).append("-").append(year).append(" ").toString();
                if(!title.matches(".*[a-zA-Z]+.*")) {
                    Toast toast = Toast.makeText(context, "Please select a title", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                    toast.show();
                } else if(priority == null) {
                    Toast toast = Toast.makeText(context, "Please select a priority", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                    toast.show();
                } else {
                    taskList.add(new Task(title, date, priority));
                    new UpdateViewTask().execute(taskList);
                }
            }
        });
    }
    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id){
            case DATE_PICKER_ID:
                return new DatePickerDialog(this, pickerListener, year, month, day);
        }
        return null;
    }
    private DatePickerDialog.OnDateSetListener pickerListener = new DatePickerDialog.OnDateSetListener(){
        @Override
        public void onDateSet(DatePicker view, int selectedYear, int monthOfYear, int dayOfMonth) {
        year = selectedYear;
        month = monthOfYear;
        day = dayOfMonth;

        display.setText(new StringBuilder().append(month + 1).append("-").append(day).append("-").append(year).append(" "));
        }
    };
    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();
        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.highestP:
                if (checked)
                    priority =  "1";
                break;
            case R.id.highP:
                if (checked)
                    priority =  "2";
                break;
            case R.id.lowP:
                if (checked)
                    priority = "3";
                    break;
        }

    }

}

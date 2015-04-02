package android.supremeaa.todo.Model;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.supremeaa.todo.Controller.TaskSerializer;
import android.supremeaa.todo.R;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;

import java.util.List;

/**
 * Created by Student on 3/1/2015.
 */
public class TaskAdapter extends ArrayAdapter<Task>{
    private List<Task> tasks;
    private Context context;
    private Activity activity;
    public TaskAdapter(Context context , int textViewResourceId) {
        super(context, textViewResourceId);
    }

    public TaskAdapter(Context context, int resource, List<Task> task, Activity activity){
        super(context, resource, task);
        this.activity = activity;
        this.context = context;
        this.tasks = task;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView;

        TextView title = null;
        TextView date = null;
        TextView priority = null;

        if(view == null) {
            LayoutInflater layoutInflater;
            layoutInflater = LayoutInflater.from(getContext());
            view = layoutInflater.inflate(R.layout.list_item, null);

            Button deleteButton = (Button)view.findViewById(R.id.deleteButton);

            deleteButton.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    TaskSerializer.deleteTask(tasks, position);
                    JSONArray saveJSONArray = TaskSerializer.toJSONArray(tasks);
                    TaskSerializer.saveAppJSONFile(context, saveJSONArray);
                    activity.finish();
                    activity.startActivity(activity.getIntent());
                }
            });
        }

        title = (TextView)view.findViewById(R.id.title);
        date = (TextView)view.findViewById(R.id.date);
        priority = (TextView)view.findViewById(R.id.priority);

        Task task = tasks.get(position);

        if(task != null){
            if(title != null){
                title.setText(task.getTitle());
            }
            if(date != null){
                date.setText(task.getDate());
            }
            if(priority != null){
                priority.setText(task.getPriority());
            }
            if(task.getPriority().equalsIgnoreCase("1")){
                view.setBackgroundColor(Color.RED);
            }else if(task.getPriority().equalsIgnoreCase("2")){
                view.setBackgroundColor(Color.BLUE);
            } else if(task.getPriority().equalsIgnoreCase("3")){
                view.setBackgroundColor(Color.WHITE);
            }
        }

        return view;
    }

}

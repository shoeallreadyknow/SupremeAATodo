package android.supremeaa.todo.Model;

import android.content.Context;
import android.graphics.Color;
import android.supremeaa.todo.R;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Student on 3/1/2015.
 */
public class TaskAdapter extends  ArrayAdapter<Task>{
    public TaskAdapter(Context context , int textViewResourceId) {
        super(context, textViewResourceId);
    }
    private List<Task> tasks;

    public TaskAdapter(Context context, int resource, List<Task> task){
        super(context, resource, task);
        this.tasks = task;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;

        TextView title = null;
        TextView date = null;
        TextView priority = null;

        if(view == null){
            LayoutInflater layoutInflater;
            layoutInflater = LayoutInflater.from(getContext());
            view = layoutInflater.inflate(R.layout.list_item, null);

            title = (TextView)view.findViewById(R.id.title);
            date = (TextView)view.findViewById(R.id.date);
            priority = (TextView)view.findViewById(R.id.priority);
        }

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

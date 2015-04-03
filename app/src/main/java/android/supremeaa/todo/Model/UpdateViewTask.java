package android.supremeaa.todo.Model;

import android.os.AsyncTask;
import android.supremeaa.todo.Controller.TaskSerializer;
import android.supremeaa.todo.TodoActivity;

import org.json.JSONArray;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Philipp on 4/2/2015.
 */
public class UpdateViewTask extends AsyncTask<List<Task>, Void, List<Task>> {

    static class TaskCompare implements Comparator<Task> {
        @Override
        public int compare(Task o1, Task o2) {
            // write comparison logic here like below , it's just a sample
            return o1.getPriority().compareTo(o2.getPriority());
        }
    }

    @Override
    protected List<Task> doInBackground(List<Task>... lists) {
        List<Task> taskList = lists[0];

        Collections.sort(taskList, new TaskCompare());
        JSONArray jsonArray = TaskSerializer.toJSONArray(taskList);
        TaskSerializer.saveAppJSONFile(TodoActivity.context, jsonArray);
        jsonArray = TaskSerializer.parseJSONFromFile(TodoActivity.context);
        taskList = TaskSerializer.toTaskList(jsonArray);

        return taskList;
    }

    @Override
    protected void onPostExecute(List<Task> taskList) {
        TodoActivity.adapter.notifyDataSetChanged();
        super.onPostExecute(taskList);
    }
}

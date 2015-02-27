package android.supremeaa.todo.Model;

/**
 * Created by Student on 2/26/2015.
 */
public class Task {
    public String title;
    public String date;
    public int priority;

    public Task(String title, String date, int priority){
        // Sets all the blog post data
        this.title = title;
        this.date = date;
        this.priority = priority;
    }
    public String getTitle(){
        return title;
    }
    public void setTitle(String title){
        this.title = title;
    }
    public String getDate(){
        return date;
    }
    public void setDate(String date){
        this.date = date;
    }
    public int getPriority(){
        return priority;
    }
    public void setPriority(int priority){
        this.priority = priority;
    }

}

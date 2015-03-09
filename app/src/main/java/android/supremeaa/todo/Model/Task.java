package android.supremeaa.todo.Model;

/**
 * Created by Student on 2/26/2015.
 */
public class Task {
    public String title;
    public String date;
    public String priority;

    public Task(String title, String date, String priority){
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
    public String getPriority(){
        return priority;
    }
    public void setPriority(String priority){
        this.priority = priority;
    }

}

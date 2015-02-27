package android.supremeaa.todo.Model;

import org.json.JSONObject;

/**
 * Created by Student on 2/26/2015.
 */
public interface JSONable {
    JSONObject toJSONObject();
    void fromJSONObject(final JSONObject src);

}

package okhttp;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by bala on 19/10/17.
 */

public class Menu {

    @SerializedName("id")
    String id;

    @SerializedName("value")
    String value;

    @SerializedName("menuitem")
    List<MenuItem> menuitem;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public List<MenuItem> getMenuitem() {
        return menuitem;
    }

    public void setMenuitem(List<MenuItem> menuitem) {
        this.menuitem = menuitem;
    }
}

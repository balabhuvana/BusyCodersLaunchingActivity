package okhttp;

import com.google.gson.annotations.SerializedName;

/**
 * Created by bala on 19/10/17.
 */

public class MenuItem {

    @SerializedName("name")
    String name;

    @SerializedName("type")
    String type;
}

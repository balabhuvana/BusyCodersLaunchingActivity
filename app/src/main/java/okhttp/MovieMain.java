package okhttp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by bala on 18/10/17.
 */

public class MovieMain {
    @SerializedName("data")
    @Expose
    private List<MovieData> data;

    public List<MovieData> getData() {
        return data;
    }

    public void setData(List<MovieData> data) {
        this.data = data;
    }
}

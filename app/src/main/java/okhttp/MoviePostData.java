package okhttp;

import com.google.gson.annotations.SerializedName;

/**
 * Created by bala on 18/10/17.
 */

public class MoviePostData {
    @SerializedName("name")
    public String name;

    @SerializedName("job")
    public String job;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }
}

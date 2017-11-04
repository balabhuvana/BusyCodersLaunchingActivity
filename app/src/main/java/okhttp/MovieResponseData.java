package okhttp;

import com.google.gson.annotations.SerializedName;

/**
 * Created by bala on 18/10/17.
 */

public class MovieResponseData {

    @SerializedName("job")

    public String job;

    @SerializedName("id")
    public String id;

    @SerializedName("createdAt")
    public String createdAt;


    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }


}

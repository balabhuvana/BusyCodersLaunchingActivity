package model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by bala on 19/10/17.
 */

public class UserResponse {

    @SerializedName("name")
    public String name;

    @SerializedName("job")
    public String job;

    @SerializedName("id")
    public String id;

    @SerializedName("createdAt")
    public String createdAt;

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

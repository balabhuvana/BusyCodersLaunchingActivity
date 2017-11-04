package model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by bala on 19/10/17.
 */

public class User {

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

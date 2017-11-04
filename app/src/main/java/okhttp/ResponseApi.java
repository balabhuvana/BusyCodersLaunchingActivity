package okhttp;

import com.google.gson.annotations.SerializedName;

/**
 * Created by bala on 18/10/17.
 */

public class ResponseApi {


    @SerializedName("title")
    private String title;

    @SerializedName("description")
    private String description;

    @SerializedName("youtubeID")
    private String youtubeID;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getYoutubeID() {
        return youtubeID;
    }

    public void setYoutubeID(String youtubeID) {
        this.youtubeID = youtubeID;
    }
}
package okhttp;

import com.google.gson.annotations.SerializedName;

/**
 * Created by bala on 18/10/17.
 */

public class MovieData {
    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

    @SerializedName("year")
    private String year;

    @SerializedName("color")
    private String color;

    @SerializedName("pantone_value")
    private String pantone_value;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getPantone_value() {
        return pantone_value;
    }

    public void setPantone_value(String pantone_value) {
        this.pantone_value = pantone_value;
    }
}

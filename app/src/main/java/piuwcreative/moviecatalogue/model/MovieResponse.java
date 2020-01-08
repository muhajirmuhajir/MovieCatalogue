package piuwcreative.moviecatalogue.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class MovieResponse {
    @SerializedName("results")
    private ArrayList<MovieModel> result;

    public ArrayList<MovieModel> getResult() {
        return result;
    }
}

package piuwcreative.moviecatalogue.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class TvResponse {
    @SerializedName("results")
    private ArrayList<TvModel> result;

    public ArrayList<TvModel> getResult() {
        return result;
    }
}

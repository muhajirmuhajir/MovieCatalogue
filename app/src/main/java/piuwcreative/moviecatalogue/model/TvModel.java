package piuwcreative.moviecatalogue.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.lang.annotation.Annotation;

@Entity(tableName = "tv_table")
public class TvModel implements SerializedName, Parcelable {
    @PrimaryKey()
    @SerializedName("id")
    @NonNull
    private int id;

    @SerializedName("name")
    @ColumnInfo(name = "title")
    private String title;

    @SerializedName("overview")
    @ColumnInfo(name = "overview")
    private String overview;

    @SerializedName("first_air_date")
    @ColumnInfo(name = "release_date")
    private String releseDate;

    @SerializedName("poster_path")
    @ColumnInfo(name = "poster_path")
    private String poster;

    @SerializedName("backdrop_path")
    @ColumnInfo(name = "backdrop_path")
    private String backdrop;

    @SerializedName("vote_average")
    @ColumnInfo(name = "vote_average")
    private double rating;

    @SerializedName("vote_count")
    @ColumnInfo(name = "vote_count")
    private int voteCount;

    public TvModel() {
    }

    @Ignore
    public TvModel(int id, String title, String overview, String releseDate, String poster, String backdrop, double rating, int voteCount) {
        this.id = id;
        this.title = title;
        this.overview = overview;
        this.releseDate = releseDate;
        this.poster = poster;
        this.backdrop = backdrop;
        this.rating = rating;
        this.voteCount = voteCount;
    }

    protected TvModel(Parcel in) {
        id = in.readInt();
        title = in.readString();
        overview = in.readString();
        releseDate = in.readString();
        poster = in.readString();
        backdrop = in.readString();
        rating = in.readDouble();
        voteCount = in.readInt();
    }

    public static final Creator<TvModel> CREATOR = new Creator<TvModel>() {
        @Override
        public TvModel createFromParcel(Parcel in) {
            return new TvModel(in);
        }

        @Override
        public TvModel[] newArray(int size) {
            return new TvModel[size];
        }
    };

    @NonNull
    public int getId() {
        return id;
    }


    public void setId(@NonNull int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getReleseDate() {
        return releseDate;
    }

    public void setReleseDate(String releseDate) {
        this.releseDate = releseDate;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getBackdrop() {
        return backdrop;
    }

    public void setBackdrop(String backdrop) {
        this.backdrop = backdrop;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public int getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(int voteCount) {
        this.voteCount = voteCount;
    }

    @Override
    public String value() {
        return null;
    }

    @Override
    public String[] alternate() {
        return new String[0];
    }

    @Override
    public Class<? extends Annotation> annotationType() {
        return null;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(title);
        parcel.writeString(overview);
        parcel.writeString(releseDate);
        parcel.writeString(poster);
        parcel.writeString(backdrop);
        parcel.writeDouble(rating);
        parcel.writeInt(voteCount);
    }
}


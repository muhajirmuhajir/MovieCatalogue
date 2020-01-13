package id.piuwcreative.favoritecatalogue.model;

import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;


public class TvModel implements Parcelable {

    @NonNull
    private int id;


    private String title;


    private String overview;


    private String releseDate;


    private String poster;

    private String backdrop;

    private double rating;


    private int voteCount;

    public TvModel() {
    }

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

    public TvModel(Cursor cursor) {
        setId(cursor.getInt(cursor.getColumnIndexOrThrow("id")));
        setTitle(cursor.getString(cursor.getColumnIndexOrThrow("title")));
        setPoster(cursor.getString(cursor.getColumnIndexOrThrow("poster_path")));
        setBackdrop(cursor.getString(cursor.getColumnIndexOrThrow("backdrop_path")));
        setOverview(cursor.getString(cursor.getColumnIndexOrThrow("overview")));
        setRating(cursor.getDouble(cursor.getColumnIndexOrThrow("vote_average")));
        setReleseDate(cursor.getString(cursor.getColumnIndexOrThrow("release_date")));
        setVoteCount(cursor.getInt(cursor.getColumnIndexOrThrow("vote_count")));
    }
}


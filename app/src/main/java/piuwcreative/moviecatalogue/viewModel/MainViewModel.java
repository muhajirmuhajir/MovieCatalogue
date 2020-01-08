package piuwcreative.moviecatalogue.viewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;
import piuwcreative.moviecatalogue.BuildConfig;
import piuwcreative.moviecatalogue.model.MovieModel;

public class MainViewModel extends ViewModel {
    private final static String API_KEY = BuildConfig.API_KEY;
    private MutableLiveData<ArrayList<MovieModel>> listMovie = new MutableLiveData<>();
    private MutableLiveData<ArrayList<JSONObject>> listGenres = new MutableLiveData<>();

    private void setData(final String type, String language) {
        AsyncHttpClient client = new AsyncHttpClient();
        String url = "https://api.themoviedb.org/3/discover/" + type + "?api_key=" + API_KEY + "&language=" + language;
        final ArrayList<MovieModel> movieModels = new ArrayList<>();

        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String result = new String(responseBody);
                try {
                    ArrayList<JSONObject> listGenres;
                    JSONObject objectResponse = new JSONObject(result);
                    JSONArray listData = objectResponse.getJSONArray("results");
                    for (int i = 0; i < listData.length(); i++) {
                        JSONObject object = listData.getJSONObject(i);
                        MovieModel model = new MovieModel();
                        model.setId(object.getInt("id"));
                        model.setTitle(object.getString(type.equals("movie")?"original_title":"original_name"));
                        model.setDescription(object.getString("overview"));
                        model.setYear(object.getString(type.equals("movie")?"release_date":"first_air_date"));
                        model.setRating(object.getDouble("vote_average") +"/10");
                        model.setPoster("https://image.tmdb.org/t/p/w185"+object.getString("poster_path"));

                        JSONArray idGenres = object.getJSONArray("genre_ids");
                        for (int j = 0; j <idGenres.length(); j++) {
                            int id = idGenres.getInt(j);
                            listGenres = MainViewModel.this.listGenres.getValue();
                            if (listGenres != null) {
                                for (JSONObject o : listGenres) {
                                    if (o.getInt("id") == id) {
                                        String genres = o.getString("name");
                                        model.setGenres(model.getGenres()+genres+", ");
                                    }
                                }
                            }

                        }
                        movieModels.add(model);
                    }

                    listMovie.postValue(movieModels);


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
    }

    public LiveData<ArrayList<MovieModel>> getData() {
        return listMovie;
    }


    public void setGenres(final String type, final String language) {
        String url = "https://api.themoviedb.org/3/genre/"+type+"/list?api_key="+API_KEY+"&language="+language;
        AsyncHttpClient client = new AsyncHttpClient();

        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String result = new String(responseBody);
                ArrayList<JSONObject> listGenres = new ArrayList<>();

                try {
                    JSONObject objectResponse = new JSONObject(result);

                    JSONArray array = objectResponse.getJSONArray("genres");
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject genre = array.getJSONObject(i);
                        listGenres.add(genre);

                    }
                    setData(type, language);
                    MainViewModel.this.listGenres.postValue(listGenres);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
    }


}

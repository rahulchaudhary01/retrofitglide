package retrofitusage;

import com.appstry.android.retrofitglide.MovieDetails;
import com.appstry.android.retrofitglide.Movies;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by RAHUL CHAUDHARY on 12/5/2016.
 */
public interface MovieService {
    @GET("3/discover/movie?sort_by=popularity.desc&api_key=8aa3c598a0c67ecf36413f81c411c555")
    Call<MovieDetails> getMoviesList();
}

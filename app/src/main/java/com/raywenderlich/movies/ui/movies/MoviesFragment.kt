package com.raywenderlich.movies.ui.movies

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.raywenderlich.movies.R
import com.raywenderlich.movies.apiService.ApiService
import com.raywenderlich.movies.models.Movie
import com.raywenderlich.movies.models.MovieResult
import com.raywenderlich.movies.models.MoviesResponse
import kotlinx.android.synthetic.main.fragment_movies.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MoviesFragment : Fragment() {

    private val apiKey = "f5b6abcb07a14d121bdd075f9134a831"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val actionBarTitle = activity?.findViewById<TextView>(R.id.custom_action_bar)
        if (actionBarTitle != null) {
            actionBarTitle.text = getString(R.string.title_movies)
        }
        return inflater.inflate(R.layout.fragment_movies, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val movies: ArrayList<Movie> = ArrayList<Movie>()

        var recyclerView = movies_recycler_view
        recyclerView.layoutManager = LinearLayoutManager(this.context, RecyclerView.VERTICAL, false)
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(ApiService::class.java)
        service.getMostPopularMovies(apiKey).enqueue(object : Callback<MoviesResponse>{
            override fun onResponse(
                call: Call<MoviesResponse>,
                response: Response<MoviesResponse>
            ) {
                var moviesList: MoviesResponse? = response.body()
                Log.d("moviesList:", response.toString())
                Toast.makeText(context, moviesList!!.results.toString(), Toast.LENGTH_SHORT).show()
                for (movie: MovieResult in moviesList.getMoviesResult()) {
                    movies.add(Movie(movie.original_title, movie.release_date, "https://image.tmdb.org/t/p/w500"+movie.backdrop_path, "https://image.tmdb.org/t/p/w500"+movie.poster_path,movie.overview, movie.id, movie.vote_average))
                }
                val adapter = MovieAdapter(movies)
                recyclerView.adapter = adapter
            }
            override fun onFailure(call: Call<MoviesResponse>, t: Throwable) {
                Toast.makeText(context, t.message, Toast.LENGTH_SHORT).show()
            }
        })

//        val retrofit: Retrofit = Retrofit.Builder()
//            .baseUrl("https://api.themoviedb.org/3/")
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//        val service = retrofit.create(ApiService::class.java)
//        service.getMostPopularMovies(apiKey).enqueue(object: Callback<MoviesResponse>{
//            override fun onResponse(
//                call: Call<MoviesResponse>,
//                response: Response<MoviesResponse>
//            ) {
//                var moviesList: MoviesResponse? = response.body()
//                Log.d("moviesList:", moviesList.toString())
//                Toast.makeText(context, moviesList!!.results.toString(), Toast.LENGTH_SHORT).show()
//                for (movie: MovieResult in moviesList!!.getMoviesResult()) {
//                    movies.add(Movie(movie.original_title, movie.release_date, "https://image.tmdb.org/t/p/w500"+movie.backdrop_path, movie.overview, movie.id, movie.vote_average))
//                }
//            }
//            override fun onFailure(call: Call<MoviesResponse>, t: Throwable) {
//                Toast.makeText(context, t.message, Toast.LENGTH_SHORT).show()
//            }
//        })
//        list_recycler_view.apply {
//            layoutManager = LinearLayoutManager(activity)
//            adapter = MovieAdapter(movies)
//        }
    }

    companion object {
        fun newInstance(): MoviesFragment = MoviesFragment()
    }

}

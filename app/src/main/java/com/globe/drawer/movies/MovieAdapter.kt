package com.globe.drawer.movies


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.globe.drawer.R

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    var movies: ArrayList<Movie> = ArrayList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }
    var onItemClickedListener: OnItemClickedListener? = null
    // var onItemClicked: ((Int) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        // UI creation per item in array
        // similar to setContentView but not for the whole activity

        val layoutInflater = LayoutInflater.from(parent.context)

        val itemView = layoutInflater.inflate(R.layout.item_movie, parent, false)

        return MovieViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        // display actual UI images, text, rating
        // minimal computing for less latency since this function is always called whenever user scrolls
        // rvMovie.addOnItemTouchListener(movieAdapter)

        val movie : Movie = movies.get(position)

        holder.tvTitle.text = movie.title
        holder.rbRating.rating = movie.rating
        holder.ivMoviePoster.setImageResource(movie.poster)
        holder.tvYear.text = "(" + movie.year + ")"

        holder.itemView.setOnClickListener(object : View.OnClickListener{
            override fun onClick(p0: View?) {
                // val clickedMovie = movies.get(holder.adapterPosition)

                onItemClickedListener?.onItemClicked(holder.adapterPosition)
                // Log.d("MovieAdapter", "Clicked movie: $clickedMovie")
            }

        } )
    }

    override fun getItemCount(): Int {
        // how many items will be displayed

        return movies.size
    }

    class MovieViewHolder(itemView:View) : ViewHolder(itemView){
        val ivMoviePoster : ImageView = itemView.findViewById(R.id.ivDesPoster)
        val tvTitle : TextView = itemView.findViewById(R.id.tvTitle)
        val rbRating : RatingBar = itemView.findViewById(R.id.rbRating)
        val tvYear : TextView = itemView.findViewById(R.id.tvYear)
    }
}



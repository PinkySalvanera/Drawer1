package com.globe.drawer.movies


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import com.globe.drawer.Constants
import com.globe.drawer.R


class MovieDescriptionActivity : AppCompatActivity() {

    private lateinit var ivPoster : ImageView
    private lateinit var tvTitle : TextView
    private lateinit var rbRating : RatingBar
    private lateinit var tvPlot : TextView
    private lateinit var tvYear : TextView
    private lateinit var tvCast : TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_description)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        ivPoster = findViewById(R.id.ivDesPoster)
        tvTitle = findViewById(R.id.tvDesTitle)
        rbRating = findViewById(R.id.rbDesRating)
        tvPlot = findViewById(R.id.tvDPlot)
        tvYear = findViewById(R.id.tvDYear)
        tvCast = findViewById(R.id.tvDCast)

        val poster = intent.getIntExtra(Constants.POSTER, 0)
        val title = intent.getStringExtra(Constants.TITLE)
        val rating = intent.getFloatExtra(Constants.RATING, 0.0F)
        val plot = intent.getStringExtra(Constants.PLOT)
        val year = intent.getStringExtra(Constants.YEAR)
        val cast = intent.getStringExtra(Constants.CAST)

        ivPoster.setImageResource(poster)
        tvTitle.text = title
        rbRating.rating = rating
        tvPlot.text = plot
        tvYear.text = year
        tvCast.text = cast

        // Log.d("MovieDescriptionAct", "$plot")

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
            }
        }
        return true
    }

}
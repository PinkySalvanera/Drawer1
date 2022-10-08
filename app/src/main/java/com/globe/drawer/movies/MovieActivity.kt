package com.globe.drawer.movies


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.globe.drawer.Constants
import com.globe.drawer.R

class MovieActivity : AppCompatActivity() {

    private lateinit var rvMovie : RecyclerView
    private val movieAdapter = MovieAdapter()

    val movies: ArrayList <Movie> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie)

        //actionBar?.setHomeButtonEnabled(true)

        rvMovie = findViewById(R.id.rvMovies)
        initializeMovies()

        movieAdapter.onItemClickedListener=object:OnItemClickedListener{
            override fun onItemClicked(position: Int) {
                val selectedMovie = movieAdapter.movies.get(position)
                Log.d("MainActivity", "Selected Movie:$selectedMovie")
                showMovieDetails(position)
            }
        }
    }

    private fun showMovieDetails(position: Int){
        val selectedMovie : Movie = movieAdapter.movies.get(position)

        val movieDetailIntent = Intent(this, MovieDescriptionActivity::class.java)

        //movieDetailIntent.putExtra(Constants.MOVIE, selectedMovie)
        movieDetailIntent.putExtra(Constants.TITLE, selectedMovie.title)
        movieDetailIntent.putExtra(Constants.POSTER, selectedMovie.poster)
        movieDetailIntent.putExtra(Constants.RATING, selectedMovie.rating)
        movieDetailIntent.putExtra(Constants.PLOT, selectedMovie.plot)
        movieDetailIntent.putExtra(Constants.YEAR, selectedMovie.year)
        movieDetailIntent.putExtra(Constants.CAST, selectedMovie.cast)

        startActivity(movieDetailIntent)
    }

    private fun initializeMovies(){
        movies.add(Movie("The Time Traveler's Wife", R.drawable.m1, 4.8F, "Chicago librarian Henry De Tamble (Eric Bana) suffers from a rare genetic disorder that causes him to drift uncontrollably back and forth through time. On one of his sojourns, he meets the love of his life, Claire (Rachel McAdams), and they marry. But the problems and complexities of any relationship are multiplied by Henry's inability to remain in one time and place, so that he and his beloved are continually out of sync.", "2009", "Eric Bana, Rachel McAdams, Hailey McCann"))
        movies.add(Movie("Clueless", R.drawable.m2, 4.0F, "Shallow, rich and socially successful Cher (Alicia Silverstone) is at the top of her Beverly Hills high school's pecking scale. Seeing herself as a matchmaker, Cher first coaxes two teachers into dating each other. Emboldened by her success, she decides to give hopelessly klutzy new student Tai (Brittany Murphy) a makeover. When Tai becomes more popular than she is, Cher realizes that her disapproving ex-stepbrother (Paul Rudd) was right about how misguided she was -- and falls for him.", "1995", "Alicia Silverstone, Paul Rudd, Brittany Murphey"))
        movies.add(Movie("Little Women", R.drawable.m3, 4.5F,"In the years after the Civil War, Jo March lives in New York and makes her living as a writer, while her sister Amy studies painting in Paris. Amy has a chance encounter with Theodore, a childhood crush who proposed to Jo but was ultimately rejected. Their oldest sibling, Meg, is married to a schoolteacher, while shy sister Beth develops a devastating illness that brings the family back together.", "2019", "Saoirse Ronan, Florence Pugh, Timothee Chalamet"))
        movies.add(Movie("Harry Potter", R.drawable.m4, 4.2F,"An 11-year-old orphan living with his unwelcoming aunt, uncle, and cousin, who learns of his own fame as a wizard known to have survived his parents' murder at the hands of the dark wizard Lord Voldemort as an infant when he is accepted to Hogwarts School of Witchcraft and Wizardry.", "2001", "Daniel Radcliffe, Emma Watson, Rupert Grint"))
        movies.add(Movie("Catch Me If You Can", R.drawable.m5, 4.9F,"Frank Abagnale, Jr. (Leonardo DiCaprio) worked as a doctor, a lawyer, and as a co-pilot for a major airline -- all before his 18th birthday. A master of deception, he was also a brilliant forger, whose skill gave him his first real claim to fame: At the age of 17, Frank Abagnale, Jr. became the most successful bank robber in the history of the U.S. FBI Agent Carl Hanratty (Tom Hanks) makes it his prime mission to capture Frank and bring him to justice, but Frank is always one step ahead of him.", "2002", "Leonardo DiCaprio, Tom Hanks"))
        movies.add(Movie("The Hunger Games", R.drawable.m6, 4.7F,"In what was once North America, the Capitol of Panem maintains its hold on its 12 districts by forcing them each to select a boy and a girl, called Tributes, to compete in a nationally televised event called the Hunger Games. Every citizen must watch as the youths fight to the death until only one remains. District 12 Tribute Katniss Everdeen (Jennifer Lawrence) has little to rely on, other than her hunting skills and sharp instincts, in an arena where she must weigh survival against love.", "2012", "Jennifer Lawrence, Josh Hutcherson"))
        movies.add(Movie("Emma", R.drawable.m7, 4.6F,"Following the antics of a young woman, Emma Woodhouse, who lives in Georgian- and Regency-era England and occupies herself with matchmaking - in sometimes misguided, often meddlesome fashion- in the lives of her friends and family.", "2020", "Anya Taylor-Joy"))
        movies.add(Movie("Whiplash", R.drawable.m8, 5.0F,"Andrew Neiman (Miles Teller) is an ambitious young jazz drummer, in pursuit of rising to the top of his elite music conservatory. Terence Fletcher (J.K. Simmons), an instructor known for his terrifying teaching methods, discovers Andrew and transfers the aspiring drummer into the top jazz ensemble, forever changing the young man's life. But Andrew's passion to achieve perfection quickly spirals into obsession, as his ruthless teacher pushes him to the brink of his ability and his sanity.", "2014", "Miles Teller, J.K. Simmons"))
        movies.add(Movie("The Martian", R.drawable.m9, 5.0F,"When astronauts blast off from the planet Mars, they leave behind Mark Watney (Matt Damon), presumed dead after a fierce storm. With only a meager amount of supplies, the stranded visitor must utilize his wits and spirit to find a way to survive on the hostile planet. Meanwhile, back on Earth, members of NASA and a team of international scientists work tirelessly to bring him home, while his crew mates hatch their own plan for a daring rescue mission.", "2015", "Matt Damon, Jessica Chastain"))
        movies.add(Movie("Interstellar", R.drawable.m10, 4.8F,"In Earth's future, a global crop blight and second Dust Bowl are slowly rendering the planet uninhabitable. Professor Brand (Michael Caine), a brilliant NASA physicist, is working on plans to save mankind by transporting Earth's population to a new home via a wormhole. But first, Brand must send former NASA pilot Cooper (Matthew McConaughey) and a team of researchers through the wormhole and across the galaxy to find out which of three planets could be mankind's new home.", "2014", "Matthew McConaughy, Jessica Chastain"))

        movieAdapter.movies = movies

        rvMovie.adapter = movieAdapter
    }
}
package com.amadydev.alkemymoviechallenge.presentation.view

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.amadydev.alkemymoviechallenge.data.DB
import com.amadydev.alkemymoviechallenge.databinding.ActivityMovieDetailsBinding
import com.squareup.picasso.Picasso

class MovieDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMovieDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initUI()
        onClickEvents()
    }

    private fun onClickEvents() {
        with(binding){
            btnClose.setOnClickListener { onBackPressed() }

        }
    }

    @SuppressLint("SetTextI18n")
    private fun initUI() {
        val id = intent.extras?.get("INTENT_ID")
        val posterPath = intent.extras?.get("INTENT_POSTER_PATH")
        val backdropPath = intent.extras?.get("INTENT_BACKDROP_PATH")
        val title = intent.extras?.get("INTENT_TITLE")
        val language = intent.extras?.get("INTENT_LANGUAGE")
        val overview = intent.extras?.get("INTENT_OVERVIEW")
        val voteAverage = intent.extras?.get("INTENT_VOTE_AVERAGE")
        val popularity = intent.extras?.get("INTENT_POPULARITY")

        Picasso.get().load(DB.base_img_url + posterPath).into(binding.ivPoster)
        Picasso.get().load(DB.base_img_url + backdropPath).into(binding.ivBackdrop)
        binding.tvLanguage.text = language.toString().uppercase()
        binding.tvOverview.text = overview.toString()
        binding.tvPopularity.text = "Popularity: ${popularity.toString().uppercase()}"
        binding.tvAverage.text = "${voteAverage.toString()}/10"
        binding.ivBackdrop.contentDescription = "${title.toString()}-$id"
        binding.ivPoster.contentDescription = "${title.toString()}-$id"
    }
}
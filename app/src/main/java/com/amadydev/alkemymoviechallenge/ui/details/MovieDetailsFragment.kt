package com.amadydev.alkemymoviechallenge.ui.details

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.amadydev.alkemymoviechallenge.AlkemyMovieChallengeApp.Companion.prefs
import com.amadydev.alkemymoviechallenge.R
import com.amadydev.alkemymoviechallenge.data.TMDb
import com.amadydev.alkemymoviechallenge.data.remote.entities.RateMovieRequest
import com.amadydev.alkemymoviechallenge.databinding.FragmentMovieDetailsBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailsFragment : Fragment(R.layout.fragment_movie_details) {
    private val args by navArgs<MovieDetailsFragmentArgs>()
    private val movieDetailsViewModel by viewModels<MovieDetailsViewModel>()

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentMovieDetailsBinding.bind(view)

        movieDetailsViewModel.response.observe(viewLifecycleOwner, {
            Toast.makeText(parentFragment?.context, it, Toast.LENGTH_SHORT).show()
        })

        callListeners(binding)



        with(binding) {
            val movie = args.movie

            Glide.with(this@MovieDetailsFragment)
                .load(TMDb.base_img_url + movie.backdropPath)
                .error(R.drawable.ic_error)
                .listener(object : RequestListener<Drawable> {
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable>?,
                        isFirstResource: Boolean,
                    ): Boolean {
                        Glide.with(this@MovieDetailsFragment)
                            .load(TMDb.base_img_url + movie.posterPath)
                            .error(R.drawable.ic_error)
                            .into(ivPoster)
                        progressBar.isVisible = false
                        tvTitle.isVisible = true
                        tvAverage.isVisible = true
                        tvLanguage.isVisible = true
                        tvPopularity.isVisible = true
                        tvOverview.isVisible = true
                        link.isVisible = true
                        return false
                    }

                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any?,
                        target: Target<Drawable>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean,
                    ): Boolean {
                        Glide.with(this@MovieDetailsFragment)
                            .load(TMDb.base_img_url + movie.posterPath)
                            .error(R.drawable.ic_error)
                            .into(ivPoster)
                        progressBar.isVisible = false
                        tvTitle.isVisible = true
                        tvAverage.isVisible = true
                        tvLanguage.isVisible = true
                        tvPopularity.isVisible = true
                        tvOverview.isVisible = true
                        link.isVisible = true
                        return false
                    }
                })
                .into(ivBackdrop)

            tvTitle.text = movie.title
            tvLanguage.text = movie.originalLanguage.uppercase()
            tvOverview.text = movie.overview
            tvPopularity.text = movie.popularity.toString()
            tvAverage.text = "${movie.voteAverage}/10"
            ivBackdrop.contentDescription = movie.title
            ivPoster.contentDescription = movie.title
            val uri = Uri.parse("https://github.com/jeffamady/alkemy-movie-challenge")
            val intent = Intent(Intent.ACTION_VIEW, uri)

            link.apply {
                setOnClickListener {
                    context.startActivity(intent)
                }
            }
            tvLink.paint.isUnderlineText = true
            tvGithub.paint.isUnderlineText = true

        }
    }

    private fun callListeners(binding: FragmentMovieDetailsBinding) {
        with(binding) {
            etRateValue.doAfterTextChanged {
                val text = etRateValue.text.toString()
                checkValue(binding, text)
            }
            btnRate.setOnClickListener {
                val value = etRateValue.text.toString().toInt()
                val rateMovieRequest = RateMovieRequest(value)
                val id = args.movie.id
                Log.d("Movie Id", id.toString())
                rateMovie(id, rateMovieRequest)
            }
        }
    }

    private fun checkValue(binding: FragmentMovieDetailsBinding, text: String) {
        with(binding) {
            try {
                if (text.toInt() in 1..10 && text.isNotEmpty()) {
                    btnRate.isEnabled = true
                } else showError(binding)
            } catch (e: Exception) {
                showError(binding)
            }
        }
    }

    private fun showError(binding: FragmentMovieDetailsBinding) {
        Toast.makeText(
            parentFragment?.context,
            "Only positive number less than 10",
            Toast.LENGTH_SHORT
        ).show()
        binding.btnRate.isEnabled = false
    }

    private fun rateMovie(id: Int, rateMovieRequest: RateMovieRequest) {
        val sessionId = prefs.getGuestSessionId()
        movieDetailsViewModel.rateTheMovie(id, sessionId, rateMovieRequest)
    }
}
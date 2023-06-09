package com.example.ghibliapi.ui.fragments.home

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import com.example.ghibliapi.R
import com.example.ghibliapi.base.BaseFragment
import com.example.ghibliapi.data.model.Film
import com.example.ghibliapi.databinding.FragmentHomeBinding
import com.example.ghibliapi.ui.adapters.HomeAdapter
import com.example.ghibliapi.utils.IntToHourAndMinute
import com.jackandphantom.carouselrecyclerview.CarouselRecyclerview
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment() {

    private lateinit var binding: FragmentHomeBinding

    private val viewModel: HomeViewModel by viewModels()

    private val adapter by lazy {
        HomeAdapter(context = this)
    }

    private val builder by lazy {
        AlertDialog.Builder(this)
    }

    private val view by lazy {
        View.inflate(this, R.layout.dialog, null)
    }

    private val dialog by lazy {
        builder.create()
    }

    override fun onCreateView(
        parent: View?,
        name: String,
        context: Context,
        attrs: AttributeSet
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding.root
        initDialog()
        setUpAllMovies()
        setUpObservers()
    }
    private fun setUpObservers() {
        viewModel.GhibiFilmList.observe(this) {
            binding.loadingProgressBar.visibility = View.GONE
            setupRecyclerView(it)
        }
    }

    private fun setupDialogDetailsFilm(film: Film) {
        val tvName = view.findViewById<TextView>(R.id.filmName)
        tvName.text = film.title

        val tvDescription = view.findViewById<TextView>(R.id.filmDescription)
        tvDescription.text = film.description

        val tvDirector = view.findViewById<TextView>(R.id.filmDirector)
        tvDirector.text = "Director: " + film.director

        val tvProducer = view.findViewById<TextView>(R.id.filmProducer)
        tvProducer.text = "Producer: " + film.producer

        val tvYear = view.findViewById<TextView>(R.id.filmYear)
        tvYear.text = "Year: " + film.release_date.toString()

        val tvScore = view.findViewById<TextView>(R.id.movie_detail_rating)
        tvScore.text = film.rt_score.toString()

        val tvDuration = view.findViewById<TextView>(R.id.movie_detail_duration)
        tvDuration.text = IntToHourAndMinute(film.running_time)

        val imvFilmBanner = view.findViewById<ImageView>(R.id.filmBanner)
        Picasso.get().load(film.image).into(imvFilmBanner)
    }

    private fun setupRecyclerView(list: List<Film>) {
        adapter.update(list)
        val carouselRecyclerview = findViewById<CarouselRecyclerview>(R.id.recycler)
        carouselRecyclerview.adapter = adapter
        carouselRecyclerview.setInfinite(true)
        adapter.onFilmClick = this::operDialogDetailsFilm
    }

    private fun setUpAllMovies() {
        viewModel.getAllFilm()
    }

    private fun initDialog() {
        builder.setView(view)
    }

    private fun operDialogDetailsFilm(film: Film) {
        setupDialog(film)
    }

    private fun setupDialog(film: Film) {
        setupDialogDetailsFilm(film)
        dialog.show()
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        setUpCloseDialogDetails()
    }

    private fun setUpCloseDialogDetails() {
        val closeDialog = view.findViewById<ImageView>(R.id.close_dialog)
        closeDialog.setOnClickListener {
            dialog.hide()
        }
    }
}
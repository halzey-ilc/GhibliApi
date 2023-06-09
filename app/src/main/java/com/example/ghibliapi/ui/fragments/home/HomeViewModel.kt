package com.example.ghibliapi.ui.fragments.home

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ghibliapi.base.BaseViewModel
import com.example.ghibliapi.data.model.Film
import com.example.ghibliapi.domain.GhibiUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.launch
import javax.inject.Inject


class HomeViewModel @ViewModelInject constructor(private val useCase: GhibiUseCases) :
    BaseViewModel() {

    var GhibiFilmList = MutableLiveData<List<Film>>()

    fun getAllFilm(){
        viewModelScope.launch {
            GhibiFilmList.value = useCase.invoke()
        }
    }
}
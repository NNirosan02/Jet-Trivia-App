package com.nnapps.jettriviaapp.screens

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nnapps.jettriviaapp.data.DataOrException
import com.nnapps.jettriviaapp.model.QuestionItem
import com.nnapps.jettriviaapp.repository.QuestionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuestionViewModel @Inject constructor(private val repository: QuestionRepository) :
    ViewModel() {

    val data: MutableState<DataOrException<ArrayList<QuestionItem>, Boolean, Exception>> =
        mutableStateOf(
            DataOrException(null, true, Exception(""))
        )

    init {
        getAllQuestions()
    }

    private fun getAllQuestions() {
        viewModelScope.launch {
            data.value.isLoading = true
            data.value = repository.getAllQuestions()
            if (data.value.data.toString().isNotEmpty()) data.value.isLoading = false
        }
    }

    fun getTotalQuestions(): Int {
        return data.value.data?.toMutableList()?.size!!
    }
}
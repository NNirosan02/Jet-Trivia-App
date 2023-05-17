package com.nnapps.jettriviaapp.repository

import android.util.Log
import com.nnapps.jettriviaapp.data.DataOrException
import com.nnapps.jettriviaapp.model.QuestionItem
import com.nnapps.jettriviaapp.network.QuestionApi
import javax.inject.Inject

class QuestionRepository @Inject constructor(private val api: QuestionApi) {
    private val dataOrException =
        DataOrException<ArrayList<QuestionItem>,
                Boolean,
                Exception>()

    suspend fun getAllQuestions(): DataOrException<ArrayList<QuestionItem>, Boolean, Exception> {
        try {
            dataOrException.isLoading = true
            dataOrException.data = api.getAllQuestions()
            if (dataOrException.data.toString().isNotEmpty()) dataOrException.isLoading = false
        } catch (exception: Exception) {
            dataOrException.e = exception
            Log.d("Exception", "getAllQuestions: ${dataOrException.e!!.localizedMessage}")
        }
        return dataOrException
    }
}
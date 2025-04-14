package com.example.abc.domain.repo

import com.example.abc.di.module.RetrofitInstance
import com.example.abc.domain.model.YourRequestType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException

class YourRepository {
    suspend fun fetchData(): Result<List<YourRequestType>> {
        return withContext(Dispatchers.IO) {
            try {
                val request = YourRequestType(
                    category = "fruits",
                    items = listOf("Apple", "Banana", "Cherry"),
                    imageUrls = listOf("http://example.com/apple.jpg", "http://example.com/banana.jpg")
                )
                val response = RetrofitInstance.api.getSomething(request)

                if (response.isSuccessful) {
                    Result.success(response.body() ?: emptyList())
                } else {
                    Result.failure(Exception("Error: ${response.code()}"))
                }
            } catch (e: IOException) {
                Result.failure(e)
            } catch (e: HttpException) {
                Result.failure(e)
            }
        }
    }
}

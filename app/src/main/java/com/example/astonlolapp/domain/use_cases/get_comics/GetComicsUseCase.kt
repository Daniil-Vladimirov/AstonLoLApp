package com.example.astonlolapp.domain.use_cases.get_comics

import com.example.astonlolapp.data.repository.Repository
import com.example.astonlolapp.domain.model.Comics
import kotlinx.coroutines.flow.Flow

class GetComicsUseCase(private val repository: Repository) {

   operator fun invoke(): Flow<List<Comics>> {
        return repository.getComics()
    }

}
package com.example.astonlolapp.domain.use_cases.comics.get_comics

import androidx.paging.PagingData
import com.example.astonlolapp.data.repository.Repository
import com.example.astonlolapp.domain.model.Comics
import kotlinx.coroutines.flow.Flow

class GetComicsFromCacheUseCase(private val repository: Repository) {

   operator fun invoke(): Flow<PagingData<Comics>> {
        return repository.getComics()
    }

}
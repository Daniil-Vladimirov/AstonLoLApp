package com.example.astonlolapp.domain.use_cases.comics.get_selected_comics

import com.example.astonlolapp.data.repository.Repository
import com.example.astonlolapp.domain.model.Comics

class GetSelectedComicsUseCase (private val repository: Repository) {

    suspend operator fun invoke(comicsId: Int): Comics {
        return repository.getSelectedComics(comicsId = comicsId)
    }

}
package com.example.bookmark.domain.usecase
import com.example.bookmark.domain.model.Book
import com.example.bookmark.domain.model.Comment
import com.example.bookmark.domain.repository.BookRepository
import javax.inject.Inject

class AddComment @Inject constructor(private val repository : BookRepository) {
    operator fun invoke(comment: String, bookId : String){
        return repository.addComment(comment, bookId)
    }
}
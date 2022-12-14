package com.example.bookmark.presentation.features.library.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.bookmark.domain.model.Book

object LibraryDiffUtilCallback : DiffUtil.ItemCallback<Book>() {
    override fun areItemsTheSame(oldItem: Book, newItem: Book): Boolean = oldItem == newItem
    override fun areContentsTheSame(oldItem: Book, newItem: Book): Boolean = oldItem == newItem
}
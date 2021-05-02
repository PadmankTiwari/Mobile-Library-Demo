package com.example.libraryapp.ui.bookListFragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.libraryapp.R
import com.example.libraryapp.databinding.ItemBookDetailBinding
import com.example.libraryapp.model.BookInformation
import com.example.libraryapp.model.BookListResponse


class BookListAdapter(private var bookList: BookListResponse) :
    RecyclerView.Adapter<BookListAdapter.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = Holder(
        DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_book_detail,
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(getItem(position))
    }

    private fun getItem(position: Int) = bookList.result[position]

    override fun getItemCount() = bookList.result.size

    class Holder(private var itemBinding: ItemBookDetailBinding?) :
        RecyclerView.ViewHolder(itemBinding?.root as View) {
        fun bind(item: BookInformation) {
            itemBinding?.item = item
        }
    }

}
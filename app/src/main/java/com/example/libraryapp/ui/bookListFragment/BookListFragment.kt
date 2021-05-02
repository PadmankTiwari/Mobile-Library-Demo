package com.example.libraryapp.ui.bookListFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.libraryapp.R
import com.example.libraryapp.databinding.FragmentBookListBinding
import com.example.libraryapp.model.BookListResponse

class BookListFragment : Fragment() {
    private lateinit var bookListViewModel: BookListViewModel
    private lateinit var mBinding: FragmentBookListBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_book_list, container, false)
        bookListViewModel = ViewModelProvider(this).get(BookListViewModel::class.java)
        mBinding.bookListViewModel = bookListViewModel
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bookListViewModel.callGetAllBooks()
        observeResponse()
    }

    private fun observeResponse() {
        bookListViewModel.apiResponse.observe(viewLifecycleOwner, {
            initAdapter(it)
        })
    }

    private fun initAdapter(bookListResponse: BookListResponse) {
        val bookListAdapter = BookListAdapter(bookListResponse)
        mBinding.rvBookList.setHasFixedSize(true)
        mBinding.rvBookList.layoutManager = LinearLayoutManager(requireContext())
        mBinding.rvBookList.adapter = bookListAdapter
    }
}
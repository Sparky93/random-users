package com.example.randomuser.presentation.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.randomuser.databinding.ListFragmentBinding
import com.example.randomuser.presentation.di.Injector
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

private const val THRESHOLD = 3

class ListFragment : Fragment() {
    @Inject
    lateinit var factory: ListViewModelFactory
    private lateinit var viewModel: ListViewModel
    private lateinit var binding: ListFragmentBinding
    private val onBottomReached = { viewModel.onNeedNextPage.onNext(Unit) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ListFragmentBinding.inflate(inflater, container, false)
        (context?.applicationContext as? Injector)?.createListSubComponent()
            ?.inject(listFragment = this)
        binding.viewModel = initViewModel()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvItems.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = ListAdapter(onBottomReached, THRESHOLD)
        }
    }

    private fun initViewModel(): ListViewModel {
        viewModel = ViewModelProvider(this, factory).get(ListViewModel::class.java)
        viewModel.apply {
            compositeDisposable.apply {
                add(data.observeOn(AndroidSchedulers.mainThread())
                    .subscribe {
                        (binding.rvItems.adapter as ListAdapter).setData(it)
                    })
                add(error.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .filter { it.isNotEmpty() }
                    .subscribe {
                        Toast.makeText(context, it, Toast.LENGTH_LONG).show()
                    })
            }
            onNeedNextPage.onNext(Unit)
        }
        return viewModel
    }

    companion object {
        fun newInstance() = ListFragment()
    }
}
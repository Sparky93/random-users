package com.example.randomuser.presentation.list

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.randomuser.R
import com.example.randomuser.presentation.di.Injector
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ListFragment : Fragment() {
    @Inject
    lateinit var factory: ListViewModelFactory
    private lateinit var viewModel: ListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        (context?.applicationContext as? Injector)?.createListSubComponent()
            ?.inject(listFragment = this)
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this, factory).get(ListViewModel::class.java)
        viewModel.apply {
            compositeDisposable.add(onDataReceived
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe {
                    Log.d("AL19931003", "Size is ${it.size}")
                })
            compositeDisposable.add(onErrorReceived
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe {
                    Log.e("AL19931003", "Got an error trying to fetch random users: $it")
                })
            onNeedNextPage.onNext(0)
        }
    }

    companion object {
        fun newInstance() = ListFragment()
    }
}
package com.example.randomuser.presentation.list

import androidx.lifecycle.ViewModel
import com.example.randomuser.data.api.RandomUserService
import com.example.randomuser.data.model.RandomUser
import com.example.randomuser.data.repository.random_user.RandomUserRepositoryImpl
import com.example.randomuser.data.repository.random_user.datasource.RandomUserCacheDataSourceImpl
import com.example.randomuser.data.repository.random_user.datasource.RandomUserLocalDataSourceImpl
import com.example.randomuser.data.repository.random_user.datasource.RandomUserRemoteDataSourceImpl
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import io.reactivex.subjects.Subject

class ListViewModel : ViewModel() {
    val compositeDisposable: CompositeDisposable by lazy { CompositeDisposable() }

    val onNeedNextPage: Subject<Int> by lazy { PublishSubject.create() }

    val onDataReceived: Subject<List<RandomUser>> by lazy { PublishSubject.create() }

    val onErrorReceived: Subject<String> by lazy { PublishSubject.create() }

    init {
        compositeDisposable.add(onNeedNextPage.subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .subscribe { getRandomUsers(it) })
    }

    private fun getRandomUsers(page: Int) = RandomUserRepositoryImpl(
        RandomUserRemoteDataSourceImpl(RandomUserService.getApi()),
        RandomUserCacheDataSourceImpl(),
        RandomUserLocalDataSourceImpl()
    )
        .getRandomUsers(page = page, results = 20, seed = "abc")
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .doOnError { it.message?.let { msg -> onErrorReceived.onNext(msg) } }
        .subscribe { onDataReceived.onNext(it) }
        .also { compositeDisposable.add(it) }

    override fun onCleared() = compositeDisposable.dispose()
}
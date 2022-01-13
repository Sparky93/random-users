package com.example.randomuser.presentation.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.randomuser.data.model.RandomUser
import com.example.randomuser.domain.usecase.random_user.GetRandomUsersUsecase
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import io.reactivex.subjects.Subject
import java.util.concurrent.TimeUnit

private const val PAGE_LIMIT = 3

class ListViewModel(
    private val getRandomUsersUsecase: GetRandomUsersUsecase
) : ViewModel() {
    private var currentPage = 0
    private var items: MutableList<RandomUser> = mutableListOf()

    val compositeDisposable: CompositeDisposable by lazy { CompositeDisposable() }

    val onNeedNextPage: Subject<Unit> by lazy { PublishSubject.create() }

    val data: Subject<List<RandomUser>> by lazy { PublishSubject.create() }

    val error: Subject<String> by lazy { PublishSubject.create() }

    val loading: MutableLiveData<Boolean> by lazy { MutableLiveData(false) }

    init {
        compositeDisposable.add(onNeedNextPage
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .debounce(1, TimeUnit.SECONDS)
            .filter { currentPage < PAGE_LIMIT }
            .subscribe {
                loading.postValue(true)
                getRandomUsers(++currentPage)
            })
    }

    private fun getRandomUsers(page: Int) = getRandomUsersUsecase
        .execute(page = page, results = 20, seed = "abc")
        .subscribeOn(Schedulers.io())
        .observeOn(Schedulers.io())
        .filter { it.isEmpty().not() }
        .doOnNext { items.addAll(it) }
        .subscribe({
            data.onNext(items)
            loading.postValue(false)
        }, {
            it.message?.let { msg -> error.onNext(msg) }
        })
        .also { compositeDisposable.add(it) }

    override fun onCleared() = compositeDisposable.dispose()
}
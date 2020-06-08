package com.example.persons.presenters

import com.example.persons.views.BaseMoxyView
import io.reactivex.ObservableTransformer
import io.reactivex.subjects.PublishSubject
import moxy.MvpPresenter
import rx.subscriptions.CompositeSubscription

abstract class BaseMoxyPresenter<View : BaseMoxyView> : MvpPresenter<View>() {

    private val attachSubject: PublishSubject<Pair<Boolean, BaseMoxyPresenter<View>>> = PublishSubject.create()
    private val destroySubject = PublishSubject.create<Boolean>()
    private val detachSubject = PublishSubject.create<Boolean>()
    private var detachCompositeSubscription = CompositeSubscription()
    private var destroyCompositeSubscription = CompositeSubscription()

    override fun attachView(view: View) {
        super.attachView(view)
        attachSubject.onNext(true to this)
    }

    override fun detachView(view: View?) {
        super.detachView(view)
        detachSubject.onNext(true)
        detachCompositeSubscription.unsubscribe()
    }

    override fun destroyView(view: View?) {
        super.destroyView(view)
        destroyCompositeSubscription.unsubscribe()
    }

    override fun onDestroy() {
        super.onDestroy()
        destroySubject.onNext(true)
    }

    protected fun <T> unsubscribeOnDetach(): ObservableTransformer<T, T> =
        ObservableTransformer { it.takeUntil(detachSubject.take(1)) }

    protected fun <T> unsubscribeOnDestroy(): ObservableTransformer<T, T> =
        ObservableTransformer { it.takeUntil(destroySubject.take(1)) }
}

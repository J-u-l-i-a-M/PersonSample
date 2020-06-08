package com.example.persons.util

import io.reactivex.Completable
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

fun <T> Single<T>.applySchedulersSingle(
    subscribeOn: Scheduler = Schedulers.io(),
    observeOn: Scheduler = AndroidSchedulers.mainThread(),
    unsubscribeOn: Scheduler = Schedulers.io()
): Single<T> = this.subscribeOn(subscribeOn)
    .observeOn(observeOn)
    .unsubscribeOn(unsubscribeOn)

fun Completable.applySchedulersCompletable(
    subscribeOn: Scheduler = Schedulers.io(),
    observeOn: Scheduler = AndroidSchedulers.mainThread(),
    unsubscribeOn: Scheduler = Schedulers.io()
): Completable = this.subscribeOn(subscribeOn)
    .observeOn(observeOn)
    .unsubscribeOn(unsubscribeOn)

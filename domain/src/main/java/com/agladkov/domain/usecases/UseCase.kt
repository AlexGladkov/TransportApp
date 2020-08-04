package com.agladkov.domain.usecases

import io.reactivex.Single

interface UseCase<T, R> {
    fun execute(request: T?): Single<R>
}
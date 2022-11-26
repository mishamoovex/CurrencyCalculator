package com.mykhailo.vasylenko.common.exeption

import kotlinx.coroutines.CancellationException
import java.net.ConnectException
import java.net.UnknownHostException
import java.util.concurrent.TimeoutException

fun Exception.isNoInternetException() =
    this is TimeoutException || this is UnknownHostException || this is ConnectException

fun Exception.isCoroutineCancellation() = this is CancellationException
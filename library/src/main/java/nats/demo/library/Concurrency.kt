package nats.demo.library

import java.util.concurrent.Future

object Concurrency {

    fun <T> Iterable<Future<T>>.await(): List<T> = map { it.get() }
}

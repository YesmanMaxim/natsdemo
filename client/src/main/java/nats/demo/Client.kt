package nats.demo

import io.nats.client.Connection
import io.nats.client.Nats
import nats.demo.api.API.SQUARE_API
import nats.demo.library.Concurrency.await
import nats.demo.library.Logger
import nats.demo.library.Serialization.body
import nats.demo.library.Serialization.encode

fun main() {
    Nats.connect("nats://localhost:4222").use { nats ->
//        nats.call()
        nats.ping()
    }
}

private fun Connection.call() {
    val start = System.nanoTime()
    generateSequence(1) { it + 1 }
        .take(40)
        .map { request(SQUARE_API, encode(it)) }
        .toList()
        .await()
        .joinToString(prefix = "\n", separator = "\n") { it.body<String>() }
        .let(Logger::log)
    Logger.log("Total: ${(System.nanoTime() - start) / 1000_000}ms")
}

private fun Connection.ping() {
    repeat(100_000) {
        Logger.log("Result for $it received: ${request(SQUARE_API, encode(it)).get().body<String>()}")
    }
}

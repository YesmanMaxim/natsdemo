package nats.demo

import io.nats.client.Nats
import nats.demo.api.API.SQUARE_API
import nats.demo.library.Logger
import nats.demo.library.Serialization.body
import nats.demo.library.Serialization.encode

fun main() {
    Nats.connect("nats://localhost:4222").use { nats ->
        repeat(100) {
            Logger.log("Result for $it received: ${nats.request(SQUARE_API, encode(it)).get().body<String>()}")
        }
    }
}
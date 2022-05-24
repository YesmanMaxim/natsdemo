package nats.demo

import io.nats.client.Message
import io.nats.client.Nats
import nats.demo.api.API.SQUARE_API
import nats.demo.library.Logger
import nats.demo.library.Serialization.body
import nats.demo.library.Serialization.encode
import kotlin.random.Random

fun main() {
    Logger.log("Starting ${ProcessHandle.current().pid()} listener")
    Nats.connect("nats://localhost:4222").use { nats ->
        repeat(3) {
            nats.createDispatcher().subscribe(SQUARE_API, "lala") { process(it) }
        }
        Thread.sleep(1000 * 60 * 10)
    }
}

private fun process(message: Message) {
    val input = message.body<Long>()
    Logger.log("Processing $input")
    if (Random.nextInt(10) == 2) throw Exception()
    Thread.sleep(3000)
    val output = "Response from ${ProcessHandle.current().pid()}: ${input * input}"
    message.connection.publish(message.replyTo, encode(output))
}

package nats.demo

import io.nats.client.Connection
import io.nats.client.Message
import io.nats.client.Nats
import io.nats.client.Options
import nats.demo.api.API.SQUARE_API
import nats.demo.library.Logger
import nats.demo.library.Serialization.body
import nats.demo.library.Serialization.encode

fun main() {
    Logger.log("Starting ${ProcessHandle.current().pid()} listener")
    Nats.connect("nats://localhost:4222").use { nats ->
        repeat(3) {
            nats.createDispatcher().subscribe(SQUARE_API, "lala") { process(it, nats) }
        }
        while (true) Thread.yield()
    }
}

private fun process(message: Message, nats: Connection) {
    val input = message.body<Long>()
    Logger.log("Processing $input")
    Thread.sleep(3000)
    val output = "Response from ${ProcessHandle.current().pid()}: ${input * input}"
    nats.publish(message.replyTo, encode(output))
}

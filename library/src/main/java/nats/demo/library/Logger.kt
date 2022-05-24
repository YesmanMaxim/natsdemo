package nats.demo.library

import java.time.LocalDateTime

object Logger {
    fun log(message: String) {
        println("[${Thread.currentThread().name}] ${LocalDateTime.now()}: $message")
    }
}

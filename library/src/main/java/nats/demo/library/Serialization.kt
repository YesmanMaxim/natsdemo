package nats.demo.library

import io.nats.client.Message
import io.nats.client.api.KeyValueEntry
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.ObjectInputStream
import java.io.ObjectOutputStream

object Serialization {
    fun encode(any: Any): ByteArray =
        ByteArrayOutputStream().also {
            ObjectOutputStream(it).use { objectStream -> objectStream.writeObject(any) }
        }.toByteArray()

    fun <T> decode(bytes: ByteArray): T = ByteArrayInputStream(bytes).let {
        ObjectInputStream(it).use { objectStream -> objectStream.readObject() }
    } as T

    fun <T> Message.body(): T = decode(data)
    fun <T> KeyValueEntry.body(): T = decode(value)
}

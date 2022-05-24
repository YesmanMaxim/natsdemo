
import io.nats.client.Nats


fun main(args: Array<String>) {
    println("Hello World!")

    // Try adding program arguments via Run/Debug configuration.
    // Learn more about running applications: https://www.jetbrains.com/help/idea/running-applications.html.
    println("Program arguments: ${args.joinToString()}")
    val natsServer =  "nats://localhost:4222"
    val nats = Nats.connect(natsServer)
    val dispatcher= nats.createDispatcher { }
    dispatcher.subscribe("nats.square.service") { msg ->

    }
}

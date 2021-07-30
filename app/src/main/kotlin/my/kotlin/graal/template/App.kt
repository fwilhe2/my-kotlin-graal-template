package my.kotlin.graal.template

import com.github.ajalt.clikt.core.*
import com.github.ajalt.clikt.parameters.options.*

class HelloMyWorld: CliktCommand() {
    val name by option(help="your name")
    override fun run() {
        echo("Hello World! ${name}")
    }
}

fun main(args: Array<String>) = HelloMyWorld().main(args)


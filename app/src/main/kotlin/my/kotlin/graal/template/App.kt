package my.kotlin.graal.template

import java.io.File

import com.github.ajalt.clikt.core.*
import com.github.ajalt.clikt.parameters.options.*
import kotlinx.serialization.*
import kotlinx.serialization.json.*

@Serializable
data class PersonalInformationRecord(
    val Subscriptions: List<Subscription>,
    val Memberships: List<Membership>,
    val Donations: List<Donation>,
    val Insurances: List<Insurance>,
)

enum class PaymentInterval {
    OneTime, Monthly, Yearly, Quarterly
}

@Serializable
data class Subscription(
    val Provider: String,
    val PaymentInterval: PaymentInterval,
    val PaymentAmount: Double
)

@Serializable
data class Membership(
    val Organization: String,
    val PaymentInterval: PaymentInterval,
    val PaymentAmount: Double
)

@Serializable
data class Donation(
    val Organization: String,
    val PaymentInterval: PaymentInterval,
    val PaymentAmount: Double
)


@Serializable
data class Insurance(
    val Provider: String,
    val PaymentInterval: PaymentInterval,
    val PaymentAmount: Double
)

class HelloMyWorld: CliktCommand() {
    val name by option(help="your name")
    override fun run() {

        val txt = File("/home/florian/code/my-kotlin-graal-template/app/src/main/resources/example.json").readText()
        val personalRecord = Json.decodeFromString<PersonalInformationRecord>(txt)

        var yearlyCost = 0.0
        personalRecord.Subscriptions.forEach {
            println(it.Provider)
            println(it.PaymentInterval)
            println(it.PaymentAmount)

            if (it.PaymentInterval == PaymentInterval.OneTime) {
                yearlyCost += it.PaymentAmount
            }

            if (it.PaymentInterval == PaymentInterval.Monthly) {
                yearlyCost += it.PaymentAmount * 12
            }

            if (it.PaymentInterval == PaymentInterval.Quarterly) {
                yearlyCost += it.PaymentAmount * 4
            }

            if (it.PaymentInterval == PaymentInterval.Yearly) {
                yearlyCost += it.PaymentAmount
            }
        }

    }
}

fun main(args: Array<String>) = HelloMyWorld().main(args)


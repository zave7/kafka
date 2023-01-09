package com.kafka.stream

import java.util.Properties
import org.apache.kafka.common.serialization.Serdes
import org.apache.kafka.streams.KafkaStreams
import org.apache.kafka.streams.StreamsBuilder
import org.apache.kafka.streams.StreamsConfig

class KStreamJoinGlobalKTable {
    private val APPLICATION_NAME = "global-table-join-application"
    private val BOOTSTRAP_SERVERS = "my-kafka:9092"
    private val ADDRESS_GLOBAL_TABLE = "address_v2"
    private val ORDER_STREAM = "order"
    private val ORDER_JOIN_STREAM = "order_join"

    fun streaming() {
        val props = Properties()
        props[StreamsConfig.APPLICATION_ID_CONFIG] = APPLICATION_NAME
        props[StreamsConfig.BOOTSTRAP_SERVERS_CONFIG] = BOOTSTRAP_SERVERS
        props[StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG] = Serdes.String().javaClass
        props[StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG] = Serdes.String().javaClass

        val builder = StreamsBuilder()
        val addressGlobalTable = builder.globalTable<String, String>(ADDRESS_GLOBAL_TABLE)
        val orderStream = builder.stream<String, String>(ORDER_STREAM)

        orderStream
            .join(
                addressGlobalTable,
                { orderKey: String, _: String? -> orderKey },
                { order: String, address: String -> "$order send to $address" }
            )
            .to(ORDER_JOIN_STREAM)

        val streams = KafkaStreams(builder.build(), props)
        streams.start()
    }
}

fun main() {
    KStreamJoinGlobalKTable().streaming()
}
package com.kafka.stream

import java.util.Properties
import org.apache.kafka.common.serialization.Serdes
import org.apache.kafka.streams.KafkaStreams
import org.apache.kafka.streams.StreamsBuilder
import org.apache.kafka.streams.StreamsConfig
import org.apache.kafka.streams.kstream.KStream

class StreamFilter {
    private val APPLICATION_NAME = "streams-filter-application"
    private val BOOTSTRAP_SERVERS = "my-kafka:9092"
    private val STREAM_LOG = "stream_log"
    private val STREAM_LOG_FILTER = "stream_log_filter"

    fun streaming() {

        val props = Properties()
        props[StreamsConfig.APPLICATION_ID_CONFIG] = APPLICATION_NAME
        props[StreamsConfig.BOOTSTRAP_SERVERS_CONFIG] = BOOTSTRAP_SERVERS
        props[StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG] = Serdes.String().javaClass
        props[StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG] = Serdes.String().javaClass

        val builder = StreamsBuilder()
        val streamLog: KStream<String, String> = builder.stream(STREAM_LOG)
        streamLog
            .filter { _, value ->
                value.length > 5
            }
            .to(STREAM_LOG_FILTER)

        val kafkaStreams = KafkaStreams(builder.build(), props)
        kafkaStreams.start()
    }
}

fun main() {
    StreamFilter().streaming()
}
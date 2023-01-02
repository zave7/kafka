package com.kafka

import java.util.Properties
import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.common.serialization.StringSerializer

object KafkaConstants {
    const val TOPIC_NAME = "test"
    const val BOOTSTRAP_SERVERS = "my-kafka:9092"
    val DEFAULT_CONFIGS  = Properties().apply {
        put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, KafkaConstants.BOOTSTRAP_SERVERS)
        put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer::class.java.name)
        put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer::class.java.name)
    }
}
package com.kafka

import com.kafka.producer.CustomPartitioner
import java.util.Properties
import kotlin.reflect.KClass
import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.common.serialization.StringSerializer

object KafkaConstants {
    const val TOPIC_NAME = "test"
    const val BOOTSTRAP_SERVERS = "my-kafka:9092"
    val DEFAULT_CONFIGS = Properties().apply {
        put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS)
        put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer::class.java.name)
        put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer::class.java.name)
    }
    val CONFIGS_WITH_CUSTOM_PARTITIONER = { clazz: KClass<*> ->
        Properties().apply {
            put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS)
            put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer::class.java.name)
            put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer::class.java.name)
            put(ProducerConfig.PARTITIONER_CLASS_CONFIG, clazz.java)
        }
    }
    val CONFIGS_WITH_ADDED_OPTION = { options: List<Pair<String, Any>> ->
        Properties().apply {
            put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS)
            put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer::class.java.name)
            put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer::class.java.name)
            options.forEach {
                put(it.first, it.second)
            }
        }
    }
}
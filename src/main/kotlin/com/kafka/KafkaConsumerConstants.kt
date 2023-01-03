package com.kafka

import java.util.Properties
import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.common.serialization.StringDeserializer

object KafkaConsumerConstants {

    private const val GROUP_ID_TEST = "test-group"

    val MANUAL_COMMIT_CONFIGS = Properties().apply {
        this[ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG] = KafkaConstants.BOOTSTRAP_SERVERS
        this[ConsumerConfig.GROUP_ID_CONFIG] = GROUP_ID_TEST
        this[ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG] = StringDeserializer::class.java.name
        this[ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG] = StringDeserializer::class.java.name
        this[ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG] = false
    }

    val AUTO_COMMIT_CONFIGS = Properties().apply {
        this[ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG] = KafkaConstants.BOOTSTRAP_SERVERS
        this[ConsumerConfig.GROUP_ID_CONFIG] =  GROUP_ID_TEST
        this[ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG] = StringDeserializer::class.java.name
        this[ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG] = StringDeserializer::class.java.name
        this[ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG] = true
        this[ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG] = 60000
    }

}
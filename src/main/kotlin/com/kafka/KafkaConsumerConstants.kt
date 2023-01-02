package com.kafka

import java.util.Properties
import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.common.serialization.StringDeserializer

object KafkaConsumerConstants {

    private const val GROUP_ID_TEST = "test-group"

    val TEST_CONFIGS = Properties().apply {
        put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, KafkaConstants.BOOTSTRAP_SERVERS)
        put(ConsumerConfig.GROUP_ID_CONFIG, GROUP_ID_TEST)
        put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer::class.java.name)
        put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer::class.java.name)
    }

}
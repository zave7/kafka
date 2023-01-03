package com.kafka.consumer

import com.kafka.KafkaConstants
import com.kafka.KafkaConsumerConstants
import java.time.Duration
import mu.KotlinLogging
import org.apache.kafka.clients.consumer.KafkaConsumer
import org.apache.kafka.common.TopicPartition

class ConsumerWithExactPartition {

    companion object {
        private val logger = KotlinLogging.logger {}
        const val PARTITION_NUMBER = 0;
    }

    fun consume() {

        val consumer = KafkaConsumer<String, String>(KafkaConsumerConstants.AUTO_COMMIT_CONFIGS)
        consumer.assign( // set collection 으로 TopicPartition 객체를 파라미터로 전달한다.
            setOf(
                TopicPartition(
                    KafkaConstants.TOPIC_NAME,
                    PARTITION_NUMBER
                )
            )
        )
        while (true) {
            val records = consumer.poll(Duration.ofSeconds(1))
            for (record in records) {
                logger.info { "record:$record"}
            }
        }

    }

}

fun main() {
    ConsumerWithExactPartition().consume()
}
package com.kafka.consumer

import com.kafka.KafkaConstants
import com.kafka.KafkaConsumerConstants
import java.time.Duration
import mu.KotlinLogging
import org.apache.kafka.clients.consumer.KafkaConsumer

class ConsumerWithASyncCommit {

    companion object {
        private val logger = KotlinLogging.logger {}
    }

    fun consume() {
        val consumer: KafkaConsumer<String, String> = KafkaConsumer(KafkaConsumerConstants.MANUAL_COMMIT_CONFIGS)
        consumer.subscribe(listOf(KafkaConstants.TOPIC_NAME))

        while (true) {
            val records = consumer.poll(Duration.ofSeconds(1))
            for (record in records) {
                logger.info { "record:$record" }
            }
            consumer.commitAsync { offsets, e ->
                if (e != null)
                    println("Commit failed")
                else
                    println("Commit succeeded")
                if (e != null)
                    logger.error { "Commit failed for offsets $offsets $e" }
            }
        }
    }

}

fun main() {
    ConsumerWithASyncCommit().consume()
}
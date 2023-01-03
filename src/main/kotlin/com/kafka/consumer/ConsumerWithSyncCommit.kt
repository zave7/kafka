package com.kafka.consumer

import com.kafka.KafkaConstants
import com.kafka.KafkaConsumerConstants
import java.time.Duration
import mu.KotlinLogging
import org.apache.kafka.clients.consumer.KafkaConsumer

class ConsumerWithSyncCommit {

    companion object {
        private val logger = KotlinLogging.logger {}
    }

    fun consume() {
        val consumer = KafkaConsumer<String, String>(KafkaConsumerConstants.MANUAL_COMMIT_CONFIGS)
        consumer.subscribe(listOf(KafkaConstants.TOPIC_NAME))
        while(true) {
            val records = consumer.poll(Duration.ofSeconds(1))
            records.forEach {
                logger.info { "record: $it" }
            }
            consumer.commitSync() // 레코드에 대한 처리를 모두 끝낸 후 동기 커밋
        }
    }

}

fun main() {
    ConsumerWithSyncCommit().consume()
}
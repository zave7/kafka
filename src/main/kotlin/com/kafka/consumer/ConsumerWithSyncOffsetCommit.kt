package com.kafka.consumer

import com.kafka.KafkaConstants
import com.kafka.KafkaConsumerConstants
import java.time.Duration
import mu.KotlinLogging
import org.apache.kafka.clients.consumer.KafkaConsumer
import org.apache.kafka.common.errors.WakeupException

class ConsumerWithSyncOffsetCommit {

    companion object {
        private val logger = KotlinLogging.logger {}
        private val consumer = KafkaConsumer<String, String>(KafkaConsumerConstants.AUTO_COMMIT_CONFIGS)
    }

    fun consume() {
        consumer.subscribe(listOf(KafkaConstants.TOPIC_NAME))

        try {
            while (true) {
                val records = consumer.poll(Duration.ofSeconds(1))
                for (record in records) {
                    logger.info { record }
                }
                consumer.commitSync()
            }
        } catch (e: WakeupException) {
            logger.warn { "Wakeup consumer" }
        } finally {
            logger.warn { "Consumer close" }
            consumer.close()
        }
    }

    class ShutdownThread : Thread() {
        override fun run() {
            logger.info { "Shutdown hook" }
            consumer::wakeup //
        }
    }

}

fun main() {
    // 셧다운 훅 스레드에 커스텀 스레드 추가
    Runtime
        .getRuntime()
        .addShutdownHook(ConsumerWithSyncOffsetCommit.ShutdownThread())
    ConsumerWithSyncOffsetCommit().consume()
    // 1. ps -ef | grep ConsumerWithSyncOffsetCommit
    // 2. kill -term PID

    // 결과
    // Shutdown hook
    // Wakeup consumer
    // Consumer close
}
package com.kafka.consumer

import com.kafka.KafkaConstants
import com.kafka.KafkaConsumerConstants
import java.time.Duration
import mu.KotlinLogging
import org.apache.kafka.clients.consumer.KafkaConsumer

class SimpleConsumer {

    companion object {
        private val logger = KotlinLogging.logger {}
    }

    fun consume() {
        val consumer = KafkaConsumer<String, String>(KafkaConsumerConstants.TEST_CONFIGS)
        consumer.subscribe(listOf(KafkaConstants.TOPIC_NAME))
        while(true) {
            val records = consumer.poll(Duration.ofSeconds(1))
            records.forEach {
                logger.info { "record: $it" }
            }
        }
    }

}

fun main() {
    val produceShell = """
        bin/kafka-console-producer.sh 
        --bootstrap-server my-kafka:9092 \
        --topic test
    """
    SimpleConsumer().consume()
}
package com.kafka.producer

import com.kafka.KafkaConstants
import java.util.Properties
import mu.KotlinLogging
import org.apache.kafka.clients.producer.KafkaProducer
import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.clients.producer.ProducerRecord
import org.apache.kafka.common.serialization.StringSerializer

class SimpleProducer {

    companion object {
        private val logger = KotlinLogging.logger {}
    }

    fun send() {
        val producer = KafkaProducer<String, String>(KafkaConstants.DEFAULT_CONFIGS) // 프로듀서 생성
        val messageValue = "testMessage"
        val record = ProducerRecord<String, String>(KafkaConstants.TOPIC_NAME, messageValue) // 레코드 생성
        producer.send(record) // send 호출 이후 partitioner 와 accumulator 의 프로세스를 거친 후에 전송된다
        logger.info { record.toString() }
        producer.flush() // accumulator 에 있는 데이터를 임의로 전송
        producer.close()

    }

}

fun main() {
    val consumerShell = """
        bin/kafka-console-consumer.sh \
        --bootstrap-server my-kafka:9092 \
        --topic test
    """
    SimpleProducer().send()
}
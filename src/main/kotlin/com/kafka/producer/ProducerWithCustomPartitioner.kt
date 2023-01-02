package com.kafka.producer

import com.kafka.KafkaConstants
import org.apache.kafka.clients.producer.KafkaProducer
import org.apache.kafka.clients.producer.ProducerRecord

class ProducerWithCustomPartitioner {

    fun send() {
        val producer = KafkaProducer<String, String>(
            KafkaConstants.CONFIGS_WITH_CUSTOM_PARTITIONER(CustomPartitioner::class)
        )
        val record = ProducerRecord(KafkaConstants.TOPIC_NAME, "Pangyo", "Pangyo")
        producer.send(record)
        producer.flush()
        producer.close()
    }

}

fun main() {
    ProducerWithCustomPartitioner().send()
}
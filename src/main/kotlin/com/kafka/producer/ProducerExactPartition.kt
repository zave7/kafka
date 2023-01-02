package com.kafka.producer

import com.kafka.KafkaConstants
import com.kafka.KafkaProducerConstants
import org.apache.kafka.clients.producer.KafkaProducer
import org.apache.kafka.clients.producer.ProducerRecord

class ProducerExactPartition {

    fun send() {
        val producer = KafkaProducer<String, String>(KafkaProducerConstants.DEFAULT_CONFIGS)
        val partitionNo = 0
        // 레코드에 특정 파티션 번호를 지정할 수 있다.
        // 파티션을 직접 지정한 경우 파티셔너를 skip 하고 바로 accumulator 로 간다.
        val record = ProducerRecord(KafkaConstants.TOPIC_NAME, partitionNo, "Pangyo", "Pangyo")
        producer.send(record)
        producer.flush()
        producer.close()
    }

}

fun main() {
    val consumerShell = """
        bin/kafka-console-consumer.sh \
        --bootstrap-server my-kafka:9092 \
        --topic test \
        --property print.key=true \
        --property key.separator="-"
    """
    ProducerExactPartition().send()
}
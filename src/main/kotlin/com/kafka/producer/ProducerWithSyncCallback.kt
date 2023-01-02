package com.kafka.producer

import com.kafka.KafkaConstants
import mu.KotlinLogging
import org.apache.kafka.clients.producer.KafkaProducer
import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.clients.producer.ProducerRecord
import org.apache.kafka.clients.producer.RecordMetadata

/**
 * KafkaProducer 의 send() 메서드는 Future 객체를 반환한다.
 * 이 객체는 RecordMetaData 의 미동기 결과를 표현하는 것으로 ProducerRecord 가 카프카 브로커에
 * 정상적으로 적재되었는지에 대한 데이터가 포함되어있다.
 * 다음과 같이 get() 메서드를 사용하면 프로듀서로 보낸 데이터의 결과를 동기적으로 가져올 수 있다.
 */
class ProducerWithSyncCallback {

    companion object {
        private val logger = KotlinLogging.logger {}
    }

    fun send() {

        val producer = KafkaProducer<String, String>(KafkaConstants.DEFAULT_CONFIGS) // 기본 acks 값 : 1 (리더 파티션 저장 여부만 확인)
        val producerAcks0 = KafkaProducer<String, String>( // 리더 파티션 저장 여부도 확인하지 않음
            KafkaConstants.CONFIGS_WITH_ADDED_OPTION(
                listOf(ProducerConfig.ACKS_CONFIG to "0")
            )
        )
        val record = ProducerRecord(KafkaConstants.TOPIC_NAME, "Pangyo", "Pangyo")
        try {
            val metadata: RecordMetadata = producerAcks0.send(record).get()
            logger.info { metadata }
        } catch (e: Exception) {
            logger.error { e.message }
        } finally {
            producer.flush()
            producer.close()
        }

    }

}

fun main() {
    ProducerWithSyncCallback().send()
    // acks(1) 결과 : test-0@28 ("test" 토픽, 0번 파티션의 28번 오프셋으로 저장됨)
    // acks(0) 결과 : test-0@-1 (0번 파티션에 전송했다는 정보만 알 수 있음, 오프셋 번호가 -1인 무의미한 값을 반환)
}
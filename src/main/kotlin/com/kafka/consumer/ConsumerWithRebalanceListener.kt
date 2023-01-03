package com.kafka.consumer

import com.kafka.KafkaConstants
import com.kafka.KafkaConsumerConstants
import java.time.Duration
import mu.KotlinLogging
import org.apache.kafka.clients.consumer.KafkaConsumer

class ConsumerWithRebalanceListener {

    companion object {
        private val logger = KotlinLogging.logger {}
        private val consumer = KafkaConsumer<String, String>(KafkaConsumerConstants.AUTO_COMMIT_CONFIGS)
    }

    fun consume() {
        consumer.subscribe(listOf(KafkaConstants.TOPIC_NAME), RebalanceListener())
        while (true) {
            val records = consumer.poll(Duration.ofSeconds(1))
            for (record in records) {
                logger.info { record }
            }
        }
    }

}

fun main() {
    ConsumerWithRebalanceListener().consume()
    // 파티션이 1개일 경우

    // 1. 컨슈머가 클러스터에 추가될 때 리밸런싱이 발생하고 파티션이 할당된다.
    // Consumer-1 Partitions are assigned : [test-0]

    // 2. 두번째 컨슈머를 추가하면 리밸런싱이 일어난 후 할당받는 파티션이 없을 경우 아이들 상태가 된다.
        // (할당받을 경우 컨슈머 1의 리밸런스 리스너의 revoke 콜백이 호출된다)
    // Consumer-2 Partitions are assigned : []
    // Consumer-1 Partitions are revoked : [test-0]
    // Consumer-1 Partitions are assigned : [test-0]

    // 3. 첫번째 컨슈머를 종료하면 파티션 리밸런싱이 일어나고 두번째 컨슈머가 [test-0] 파티션을 할당받게 된다.
    // Consumer-2 Partitions are revoked : []
    // Consumer-2 Partitions are assigned : [test-0]
}
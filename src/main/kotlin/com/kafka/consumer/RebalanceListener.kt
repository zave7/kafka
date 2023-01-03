package com.kafka.consumer

import mu.KotlinLogging
import org.apache.kafka.clients.consumer.ConsumerRebalanceListener
import org.apache.kafka.common.TopicPartition

class RebalanceListener : ConsumerRebalanceListener {

    companion object {
        private val logger = KotlinLogging.logger {}
    }

    override fun onPartitionsRevoked(partitions: MutableCollection<TopicPartition>?) {
        logger.warn { "Partitions are revoked : $partitions" }
    }

    override fun onPartitionsAssigned(partitions: MutableCollection<TopicPartition>?) {
        logger.warn { "Partitions are assigned : $partitions" }
    }
}
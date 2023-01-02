package com.kafka.producer

import org.apache.kafka.clients.producer.Partitioner
import org.apache.kafka.common.Cluster
import org.apache.kafka.common.InvalidRecordException
import org.apache.kafka.common.utils.Utils

class CustomPartitioner : Partitioner {

    override fun partition(
        topic: String?,
        key: Any?,
        keyBytes: ByteArray?,
        value: Any?,
        valueBytes: ByteArray?,
        cluster: Cluster,
    ): Int {
        if(keyBytes == null)
            throw InvalidRecordException("Need message key")
        if(key is String && key == "Pangyo")
            return 0 // 0 번 파티션으로 지정
        val partitions = cluster.partitionsForTopic(topic)
        return Utils.toPositive(Utils.murmur2(keyBytes)) % partitions.size
    }

    override fun configure(configs: MutableMap<String, *>?) {}

    override fun close() {}

}
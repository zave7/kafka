# Kafka-kotlin

## **Apache Kafka란?**

Apache Kafka는 대용량 데이터 스트림을 처리하기 위한 분산 스트리밍 플랫폼입니다. Kafka는 데이터를 실시간으로 처리하고 저장하기 위한 목적으로 설계되었습니다.

## **이 저장소의 목적**

이 저장소는 Apache Kafka를 Kotlin으로 사용하는 방법을 배우기 위한 예제 코드를 포함합니다. 예제 코드를 사용하여 Kafka의 기본적인 개념과 사용법을 익힐 수 있습니다.

## **설치하기**

**`build.gradle.kts`** 파일에 아래와 같이 의존성을 추가합니다.

```kotlin
dependencies {
    implementation("com.github.zave7:kafka-kotlin:0.1.0")
}
```

## **사용하기**

### **Producer 사용하기**

```kotlin
val producer = KafkaProducer<String, String>(properties)
val record = ProducerRecord<String, String>("test_topic", "key", "value")
producer.send(record)
producer.close()
```

### ****Consumer 사용하기****

```kotlin
val consumer = KafkaConsumer<String, String>(properties)
consumer.subscribe(listOf("test_topic"))
while (true) {
    val records = consumer.poll(Duration.ofMillis(100))
    for (record in records) {
        println(record.value())
    }
}
```

****Kotlin DSL 사용하기****

```kotlin
val consumer = kafkaConsumer<String, String> {
    bootstrapServers = "localhost:9092"
    groupId = "test_group"
    subscribe("test_topic")
    autoOffsetReset = "earliest"
    enableAutoCommit = true
}

val producer = kafkaProducer<String, String> {
    bootstrapServers = "localhost:9092"
}
```

## **기여하기**

이 저장소는 학습용으로 만들어졌기 때문에 누구나 기여할 수 있습니다. 코드나 문서에 대한 수정 및 개선 사항이 있다면 언제든지 풀리퀘스트를 보내주세요.
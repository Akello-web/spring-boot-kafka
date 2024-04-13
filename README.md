### Kafka Command Line
Here are the commands used to interact with Kafka in the project:

**Create a topic**:
```java
docker exec broker kafka-topics --bootstrap-server broker:9092 --create --topic "customer.visit"
```

**Command Line Consumer**:
```java
docker exec --interactive --tty broker kafka-console-consumer --bootstrap-server broker:9092 --topic "customer.visit" --from-beginning
```

**Command Line Producer**:
```java
docker exec --interactive --tty broker kafka-console-producer --bootstrap-server broker:9092 --topic "customer.visit"
```

### docker中kafka启动命令

`podman run -d --name kafka -p 9092:9092 -e KAFKA_BROKER_ID=0 -e KAFKA_ZOOKEEPER_CONNECT=192.168.20.18:2181 -e KAFKA_ADVERTISED_LISTENERS=PLAINTEXT://192.168.20.18:9092 -e KAFKA_LISTENERS=PLAINTEXT://0.0.0.0:9092 -e ALLOW_PLAINTEXT_LISTENER=yes  -t docker.io/bitnami/kafka
db4664a3df94e0819fc126514b5e13e5145f0f1e26cad12e3db2f5fc6adc1e7e
`
### KafkaManager启动命令
`
bin/cmak -java-home /home/chiwenda/webdevtools/openjdk-11.0.2/jdk-11.0.2

`
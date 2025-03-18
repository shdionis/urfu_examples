# Проект: Интеграция Apache Kafka и RabbitMQ
Этот проект демонстрирует настройку и использование Apache Kafka и RabbitMQ для работы с сообщениями.
Ниже приведены инструкции по установке и запуску Apache Kafka (в режимах Zookeeper и KRaft) и RabbitMQ с включением веб-менеджера.

---

## 1. Установка и запуск Apache Kafka

### 1.1 Установка Apache Kafka

1. **Скачайте Apache Kafka**:
    - Перейдите на [официальный сайт Apache Kafka](https://kafka.apache.org/downloads) и скачайте последнюю версию.
    - Распакуйте архив:
      ```bash
      tar -xzf kafka_2.13-3.2.0.tgz
      cd kafka_2.13-3.2.0
      ```

2. **Запуск Kafka в режиме Zookeeper**:
    - Запустите Zookeeper:
      ```bash
      bin/zookeeper-server-start.sh config/zookeeper.properties
      ```
    - В новом терминале запустите Kafka:
      ```bash
      bin/kafka-server-start.sh config/server.properties
      ```

3. **Запуск Kafka в режиме KRaft (без Zookeeper)**:
    - Сгенерируйте уникальный идентификатор кластера:
      ```bash
      KAFKA_CLUSTER_ID="$(bin/kafka-storage.sh random-uuid)"
      ```
    - Отформатируйте хранилище:
      ```bash
      bin/kafka-storage.sh format -t $KAFKA_CLUSTER_ID -c config/kraft/server.properties
      ```
    - Запустите Kafka:
      ```bash
      bin/kafka-server-start.sh config/kraft/server.properties
      ```

4. **Создание топика и отправка сообщений**:
    - Создайте топик:
      ```bash
      bin/kafka-topics.sh --create --topic myTopic --bootstrap-server localhost:9092
      ```
    - Отправьте сообщение:
      ```bash
      echo "Hello, Kafka!" | bin/kafka-console-producer.sh --topic myTopic --bootstrap-server localhost:9092
      ```
    - Получите сообщение:
      ```bash
      bin/kafka-console-consumer.sh --topic myTopic --from-beginning --bootstrap-server localhost:9092
      ```

---

## 2. Установка и запуск RabbitMQ

### 2.1 Установка RabbitMQ

1. **Установите RabbitMQ**:
    - Для Ubuntu/Debian:
      ```bash
      sudo apt update
      sudo apt install rabbitmq-server
      ```
    - Для macOS (с использованием Homebrew):
      ```bash
      brew install rabbitmq
      ```
- Более подробные инструкции с установкой зависимостей Erlang на [оффициальном сайте RabbitMQ](https://www.rabbitmq.com/docs/download)

2. **Запуск RabbitMQ**:
    - Запустите сервер:
      ```bash
      sudo systemctl start rabbitmq-server
      ```
    - Включите автозапуск:
      ```bash
      sudo systemctl enable rabbitmq-server
      ```

3. **Включение веб-менеджера**:
    - Установите плагин веб-менеджера:
      ```bash
      sudo rabbitmq-plugins enable rabbitmq_management
      ```
    - Перезапустите RabbitMQ:
      ```bash
      sudo systemctl restart rabbitmq-server
      ```

4. **Доступ к веб-менеджеру**:
    - Откройте браузер и перейдите по адресу:
      ```
      http://localhost:15672
      ```
    - Используйте стандартные учетные данные:
        - Логин: `guest`
        - Пароль: `guest`

---
## 4. Документация

- [Apache Kafka Documentation](https://kafka.apache.org/documentation/)
- [RabbitMQ Documentation](https://www.rabbitmq.com/documentation.html)
## Service Template

Стандартный шаблон проекта на SpringBoot

## Использованные технологии

* [Spring Boot](https://spring.io/projects/spring-boot) – как основной фрэймворк
* [PostgreSQL](https://www.postgresql.org/) – как основная реляционная база данных
* [Redis](https://redis.io/) – как кэш и очередь сообщений через pub/sub
* [testcontainers](https://testcontainers.com/) – для изолированного тестирования с базой данных
* [Liquibase](https://www.liquibase.org/) – для ведения миграций схемы БД
* [Gradle](https://gradle.org/) – как система сборки приложения
* [Lombok](https://projectlombok.org/) – для удобной работы с POJO классами
* [MapStruct](https://mapstruct.org/) – для удобного маппинга между POJO классами

## База данных

* База поднимается в отдельном сервисе [infra](../infra)
* Redis поднимается в единственном инстансе тоже в [infra](../infra)
* Liquibase сам накатывает нужные миграции на голый PostgreSql при старте приложения
* В тестах используется [testcontainers](https://testcontainers.com/), в котором тоже запускается отдельный инстанс
  postgres
* В коде продемонстрирована работа как с JPA (Hibernate)

## Notification Service - обработка и отправление нотификаций
[Общий интерфейс](https://github.com/Ikhsanov-Nail-95/notification_service/blob/main/src/main/java/faang/school/notificationservice/message/MessageBuilder.java) для реализации определённого типа [Message Builder](https://github.com/Ikhsanov-Nail-95/notification_service/blob/main/src/main/java/faang/school/notificationservice/message/LikeEventMessageBuilder.java) для построения корректного сообщения (в моём случае под мой тип нотификации), после чего выбирает предпочитаемый вид контакта пользователя и отправляет этим способом нотификацию.

[Общий интерфейс](https://github.com/Ikhsanov-Nail-95/notification_service/blob/main/src/main/java/faang/school/notificationservice/service/NotificationService.java) предоставляет основные методы для отправки нотификаций пользователю. Под каждый конкретный способ отправки (SMS, Email, Telegram...) создается реализация этого интерфейса.
В моем случае я реализовал [TelegramService](https://github.com/Ikhsanov-Nail-95/notification_service/blob/main/src/main/java/faang/school/notificationservice/service/telegram/TelegramService.java), который отвечает за отправку нотификаций пользователю в его Телеграм через нашего собственного Telegram-бота.

[Абстрактный класс](https://github.com/Ikhsanov-Nail-95/notification_service/blob/main/src/main/java/faang/school/notificationservice/service/telegram/command/Command.java) для реализации различных команд необходимых при взаимодействия с пользователем, например команда [StartCommand](https://github.com/Ikhsanov-Nail-95/notification_service/blob/main/src/main/java/faang/school/notificationservice/service/telegram/command/StartCommand.java).

Класс [CommandExecutor](https://github.com/Ikhsanov-Nail-95/notification_service/blob/main/src/main/java/faang/school/notificationservice/service/telegram/command/CommandExecutor.java) служит для выполнения команд (например, "/start", "/help"), которые могут взаимодействовать с пользователем через чат. Команды хранятся в словаре `commands`, где ключом является строка, обозначающая команду, а значением - объект класса `Command`. В методе `execute()` происходит поиск команды по переданному ключу, и если команда найдена, то вызывается метод `sendMessage()` объекта команды, который возвращает объект `SendMessage`. Если команда не найдена, то вызывается команда с ключом `/unknown`.
Таким образом, класс `CommandExecutor` обеспечивает гибкое и расширяемое выполнение команд, передаваемых в виде строк, и позволяет легко добавлять новые команды или изменять существующие.

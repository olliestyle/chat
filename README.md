Приложение job4j_chat.
<br />
REST API для приложения "Чат".
<br />
Используется 5 сущностей - Person, Role, Room, RoomDetails, Message.
<br />
Технологии:
<br />
Java 11 SpringBoot, SpringJPA, PostgreSQL.
<br />
Присутствуют следующие возможности:
<br />
- создавать комнаты, пользователей, роли, сообщения
<br />
- создавать подписки пользователей на комнаты
<br />
- назначать определенные роли для пользователя в комнате
<br />
- получать все сообщения в комнате определенного пользователя
<br />
- получать все сообщения пользователя
<br />
- получать все сообщения в комнате
<br />
- получать список всех пользователей в комнате
<br /><br />
Сборка и запуск проекта:
1. Собираем jar
<br />
<br />

```
mvn install Dmaven.test.skip=true
```
<br />
2. Собираем образ приложения:
<br />
<br />

```
docker build -t chat .
```
<br />
3. Для базы данных используем образ postgres:latest
<br />
4. Запускаем docker-compose
<br />
<br />

```
docker-compose up
```
<br />


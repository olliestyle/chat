FROM openjdk
WORKDIR chat
ADD target/chat-1.0.jar chat.jar
ENTRYPOINT java -jar chat.jar

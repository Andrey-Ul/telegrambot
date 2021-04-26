FROM adoptopenjdk/openjdk11:ubi
ARG JAR_FILE=target/*.jar
ENV BOT_NAME=test_au_telegram_bot
ENV BOT_TOKEN=1719738165:AAEHw5NON7Cg-iMxjVn28JNZ6_tuSHkKPck
RUN mkdir /opt/app
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-Dbot.username=${BOT_NAME}", "-Dbot.token=${BOT_TOKEN}", "-jar", "/app.jar"]

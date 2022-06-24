FROM openjdk:11 AS TEMP_BUILD_IMAGE
ENV APP_HOME=.
USER root
WORKDIR $APP_HOME
COPY build.gradle settings.gradle gradlew $APP_HOME
COPY gradle $APP_HOME/gradle
RUN ./gradlew clean --watch-fs build -x test|| return 0 
COPY . .
RUN ./gradlew clean --watch-fs build -x test

FROM openjdk:11
ENV ARTIFACT_NAME=nfldepthchart-0.0.1-SNAPSHOT.jar
ENV APP_HOME=.
WORKDIR $APP_HOME
COPY --from=TEMP_BUILD_IMAGE $APP_HOME/build/libs/$ARTIFACT_NAME .
EXPOSE 8080
CMD ["java", "-jar", "nfldepthchart-0.0.1-SNAPSHOT.jar"]
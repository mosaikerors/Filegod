# Docker image for springboot file run
# Version: 1.0
# Author: JY
FROM java:8
VOLUME /tmp
# add jar package and rename to jbook.jar
ADD ebook-0.0.1-SNAPSHOT.jar jbook.jar
# run it
RUN bash -c 'touch /jbook.jar'
ENV JAVA_OPTS=""
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/urandom","-jar","/jbook.jar"]

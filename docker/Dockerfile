FROM anapsix/alpine-java:8_jdk
MAINTAINER luongbangvh@gmail.com

ENV WEB_FILE server.jar
ENV SCRIPT_FILE start-server.sh
ENV WEB_DIR /opt/angular_app

RUN mkdir -p ${WEB_DIR}

COPY ${WEB_FILE} ${WEB_DIR}/${WEB_FILE}
COPY ${SCRIPT_FILE} ${WEB_DIR}/${SCRIPT_FILE}

WORKDIR ${WEB_DIR}
RUN dos2unix ${SCRIPT_FILE}
ENTRYPOINT ["./start-server.sh"]
EXPOSE 8080
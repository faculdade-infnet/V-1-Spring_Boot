# verssao utilziada + versao do jdk do java
FROM eclipse-temurin:21-jdk
# diretorio que vou utilizar
WORKDIR /app
# copia o arquivo target e renomeia ele apra "app.jar"
COPY target/myApplication-0.0.1-SNAPSHOT.jar app.jar
# Define qual porta a aplicação irá rodar
EXPOSE 8080
# comando padrão que será executado quando  oconteiner iniciar
ENTRYPOINT ["java", "-jar", "app.jar"]

#Docker version 28.4.0, build d8eb465
#PS C:\Users\User> docker ps
#CONTAINER ID   IMAGE     COMMAND   CREATED   STATUS    PORTS     NAMES
#PS C:\Users\User> docker -a
#unknown shorthand flag: 'a' in -a
#
#Usage:  docker [OPTIONS] COMMAND [ARG...]
#
#Run 'docker --help' for more information
#PS C:\Users\User> docker ps -a
#CONTAINER ID   IMAGE                                                  COMMAND                  CREATED      STATUS                      PORTS                              NAMES
#01199c1945ac   quay.io/keycloak/keycloak:24.0                         "/opt/keycloak/bin/k…"   7 days ago   Exited (255) 24 hours ago   8443/tcp, 0.0.0.0:8000->8080/tcp   keycloak-keycloak-1
#e7d969bca875   docker.elastic.co/kibana/kibana:8.14.1                 "/bin/tini -- /usr/l…"   8 days ago   Exited (255) 24 hours ago   0.0.0.0:5601->5601/tcp             kib
#917e5c8fe57b   docker.elastic.co/elasticsearch/elasticsearch:8.14.1   "/bin/tini -- /usr/l…"   8 days ago   Exited (255) 24 hours ago   0.0.0.0:9200->9200/tcp, 9300/tcp   es
#PS C:\Users\User>
FROM centos7-jdk17
MAINTAINER dxc
EXPOSE 8500
ADD target/sdzx-server-gateway-1.0-SNAPSHOT.jar /sdzx-server-gateway-1.0-SNAPSHOT.jar

WORKDIR /
ENTRYPOINT ["java" , "-jar" , "sdzx-server-gateway-1.0-SNAPSHOT.jar"]
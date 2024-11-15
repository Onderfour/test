FROM centos7-jdk17
MAINTAINER dxc
EXPOSE 8501
ADD target/sdzx-manager-1.0-SNAPSHOT.jar /sdzx-manager-1.0-SNAPSHOT.jar
RUN yum install fontconfig -y
WORKDIR /
ENTRYPOINT ["java" , "-jar" , "sdzx-manager-1.0-SNAPSHOT.jar"]
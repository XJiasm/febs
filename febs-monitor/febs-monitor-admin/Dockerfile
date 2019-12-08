FROM openjdk:8u212-jre
MAINTAINER MrBird 852252810@qq.com

COPY ./target/febs-monitor-admin-1.3-SNAPSHOT.jar /febs/febs-monitor-admin-1.3-SNAPSHOT.jar
ADD agent/ /agent

ENTRYPOINT ["java", "-javaagent:/agent/skywalking-agent.jar", "-Dskywalking.agent.service_name=febs-monitor-admin", "-Dskywalking.collector.backend_service=${skywalking.url}:11800", "-jar", "/febs/febs-monitor-admin-1.3-SNAPSHOT.jar"]
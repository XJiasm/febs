FROM openjdk:8u212-jre
MAINTAINER MrBird 852252810@qq.com

COPY ./target/febs-server-system-1.4-RELEASE.jar /febs/febs-server-system-1.4-RELEASE.jar
ADD agent/ /agent

ENTRYPOINT ["java", "-javaagent:/agent/skywalking-agent.jar", "-Dskywalking.agent.service_name=febs-server-system", "-Dskywalking.collector.backend_service=${skywalking.url}:11800", "-jar", "/febs/febs-server-system-1.4-RELEASE.jar"]
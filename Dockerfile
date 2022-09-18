FROM openjdk:11 as build
EXPOSE 8080
ARG JAR_FILE
ADD ${JAR_FILE} mc_cd_catalog.jar
ENTRYPOINT ["java","-jar","/mc_cd_catalog.jar"]


#In root project folder
# docker build --build-arg JAR_FILE=build/libs/mc_cd_catalog-0.0.1-SNAPSHOT.jar -t victor/cd-store .
#minikube image load victor/cd-store
#kubectl apply -f .\kubernetes\mc_cd_catalog\deployment.yaml
#kubectl expose deployment/cd-store --type="NodePort" --port 8080
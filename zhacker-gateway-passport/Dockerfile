FROM java:8
ADD ["target/zhacker-gateway-passport.jar", "app.jar"]
EXPOSE 80
ENTRYPOINT ["java","-jar","/app.jar"]
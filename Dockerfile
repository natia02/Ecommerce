FROM openjdk:17
COPY out/production/Ecommerce/ /temp
WORKDIR /temp
CMD javac Main.java
ENTRYPOINT ["java", "Main"]
FROM openjdk:17-alpine

WORKDIR /app

COPY target/Online-shoe-store-0.0.1-SNAPSHOT.jar .

ENTRYPOINT ["java", "-jar", "Online-shoe-store-0.0.1-SNAPSHOT.jar", "--database-driver-class=com.mysql.cj.jdbc.Driver", "--database-url=jdbc:mysql://mysql:3306/online_shoe_store_dev","--database-username=root","--database-password=root","--active-profile=dev"]
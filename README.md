# back-end-delitos
## en el directorio resources, crear un archivo llamado application.properties

server.port=8082
server.servlet.context-path=/api/v1
spring.datasource.url=jdbc:mysql://localhost:3306/helpme_iud?serverTimezone=America/Bogota
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.username=root
spring.datasource.password=

spring.jpa.hibernate.ddl-auto=none
logging.level.org.hibernate.SQL=INFO
logging.level.org.springframework.web=INFO

logging.level.root=INFO
#logging.level.co.edu.iudigital.app.test=DEBUG
logging.level.co.edu.iudigital.app.service.impl=WARN

#spring security
security.jwt.client-service=HelpmeIUD
security.jwt.password-service=123456
security.jwt.scope-read=read
security.jwt.scope-write=write
security.jwt.grant-password=password
security.jwt.grant-refresh=refresh_token
security.jwt.token-validity-seconds=3600
security.jwt.refresh-validity-seconds=3600

# envio del mail
spring.mail.host=smtp.gmail.com
spring.mail.port=587
spring.mail.username=CORREO_GMAIL_AQUI
spring.mail.password=CONTRASEÑA_AQUI
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true

#files
spring.servlet.multipart.max-file-size=20MB
spring.servlet.multipart.max-request-size=20MB
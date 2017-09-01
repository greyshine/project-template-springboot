#! /bin/sh

# adapted from https://www.drissamri.be/blog/java/enable-https-in-spring-boot/

keytool -genkey -alias tomcat -storetype PKCS12 -keyalg RSA -keysize 2048 -keystore keystore.p12 -validity 3650 
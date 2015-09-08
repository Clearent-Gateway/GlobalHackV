Requirements (run as admin privileges):
- git         - https://git-scm.com/downloads (need command line version)
- Java 8      - http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html
- Maven       - http://maven.apache.org/download.cgi
- npm(nodejs) - https://nodejs.org/download/
- bower       - npm install -g bower
- gulp        - npm install -g gulp

Confirm the following are on your classpath:
'java -version'
'mvn --version'
'npm --version'
'bower --version'

For live code concat:
'gulp default' - this is ongoing, so leave this terminal running

Run:
mvn spring-boot:run -Dspring.config.location=application.properties


if missing dependencies:
npm install
bower install

to see application:
open browser to http://localhost:8080/

If you have a settings.xml in your local maven repository
 (C:\Users\<username>\.m2\settings.xml)
 you will need to rename this file when updating project if your
 settings.xml points to a different maven repository

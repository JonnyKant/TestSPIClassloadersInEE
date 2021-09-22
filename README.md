Проект содержит ознакомительный характер в личных целях механизма реализации интерфейсов с помощью SPI в 
среде Apache Tomcat на примере JDBC.
Первое веб-приложение содержит библиотеку MySQL, второе PostgresSQL. Загрузка веб-приложений происходит в порядке: 
1. firstApp.war
2. secondApp.war
Версия JDBC > 4.0 
Для каждого war TomCat использует свой CL. 
DriverManager при вызове любого метода производит инициализацию с помощью System.getProperty("jdbc.drivers") 
и ServiceLoader, после чего приходится вызвывать вручную DriverManager.registryDriver() или .forName(), что по сути 
одно и тоже. Также сам SL вызывает forName -> static block -> DM.registryDriver().
Но DM формирует драйвера на основе CL, т.е. может содеражться N одинаковых драйверов для каждого war. 
DM имеет механизм загрузки из rt И PlatformCL с помощью ContextCL.

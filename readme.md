# ApacheMq-JMS-carService
apachemq jms service for sending and receiving objects type car

For starting the project you need to clone or download it from git.
Next one - you should run mvn clean package on terminal.
After that, you can run main classes on IntelliJ IDEA/Eclipse or you can build jar files with next  dependencies:

* for NewCarsSender Controller :

Manifest-Version: 1.0

Main-Class: controller.NewCarsSender

Class-Path: activemq-all-5.15.10.jar slf4j-api-1.7.25.jar netty-transpor
  t-native-unix-common-4.1.39.Final.jar geronimo-jms_2.0_spec-1.0-alpha-2
  .jar logback-classic-1.2.3.jar netty-transport-native-kqueue-4.1.39.Fin
  al-osx-x86_64.jar netty-resolver-4.1.39.Final.jar netty-transport-nativ
  e-epoll-4.1.39.Final-linux-x86_64.jar netty-codec-http-4.1.39.Final.jar
   logback-core-1.2.3.jar netty-handler-4.1.39.Final.jar netty-buffer-4.1
  .39.Final.jar netty-common-4.1.39.Final.jar netty-codec-4.1.39.Final.ja
  r netty-transport-4.1.39.Final.jar qpid-jms-client-0.45.0.jar proton-j-
  0.33.2.jar commons-lang3-3.3.2.jar geronimo-jms_1.1_spec-1.1.jar
  
  * for MessageFilterController :

Manifest-Version: 1.0

Main-Class: controller.MessageFilterController

Class-Path: activemq-all-5.15.10.jar slf4j-api-1.7.25.jar netty-transpor
   t-native-unix-common-4.1.39.Final.jar geronimo-jms_2.0_spec-1.0-alpha-2
   .jar logback-classic-1.2.3.jar netty-transport-native-kqueue-4.1.39.Fin
   al-osx-x86_64.jar netty-resolver-4.1.39.Final.jar netty-transport-nativ
   e-epoll-4.1.39.Final-linux-x86_64.jar netty-codec-http-4.1.39.Final.jar
    logback-core-1.2.3.jar netty-handler-4.1.39.Final.jar netty-buffer-4.1
   .39.Final.jar netty-common-4.1.39.Final.jar netty-codec-4.1.39.Final.ja
   r netty-transport-4.1.39.Final.jar qpid-jms-client-0.45.0.jar proton-j-
   0.33.2.jar commons-lang3-3.3.2.jar geronimo-jms_1.1_spec-1.1.jar
   
   * for CarStandardizerController:
   
   Manifest-Version: 1.0
   
  
   Main-Class: controller.CarStandardizerController
   
   Class-Path: activemq-all-5.15.10.jar slf4j-api-1.7.25.jar netty-transpor
    t-native-unix-common-4.1.39.Final.jar geronimo-jms_2.0_spec-1.0-alpha-2
    .jar logback-classic-1.2.3.jar netty-transport-native-kqueue-4.1.39.Fin
    al-osx-x86_64.jar netty-resolver-4.1.39.Final.jar netty-transport-nativ
    e-epoll-4.1.39.Final-linux-x86_64.jar netty-codec-http-4.1.39.Final.jar
     logback-core-1.2.3.jar netty-handler-4.1.39.Final.jar netty-buffer-4.1
    .39.Final.jar netty-common-4.1.39.Final.jar netty-codec-4.1.39.Final.ja
    r netty-transport-4.1.39.Final.jar qpid-jms-client-0.45.0.jar proton-j-
    0.33.2.jar commons-lang3-3.3.2.jar geronimo-jms_1.1_spec-1.1.jar
    
# How to run it on the terminal?

* java -Dorg.apache.activemq.SERIALIZABLE_PACKAGES=* -cp NewCarsSender.jar controller.NewCarsSender

* java -Dorg.apache.activemq.SERIALIZABLE_PACKAGES=* -cp MessageFilterController.jar controller.MessageFilterController

* java -Dorg.apache.activemq.SERIALIZABLE_PACKAGES=* -cp CarStandardizerController.jar controller.CarStandardizerController

 #Enjoy!
 
 # <a name="author"></a>Author
 * [Taras Khalak](https://github.com/tarasulo)
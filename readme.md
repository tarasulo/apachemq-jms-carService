# ApacheMq-JMS-carService
Apachemq jms service for sending and receiving objects type car

# Manual

For starting the project you need to clone or download it from git.

# using scripts:
 * create 3 small scripts with next commands:
 1) NewCarsSender:
 
 "#!/bin/bash
 
 cd $1
 
 mkdir build
 
 javac -cp ".:$2/*" -d ./build $(find . -name "*.java")
 
 cp -a ./src/main/resources/ ./build
 
 jar cfm Sender.jar ./src/main/resources/manifests/sender/META-INF/MANIFEST.MF -C build .
 
 java -Dorg.apache.activemq.SERIALIZABLE_PACKAGES=* -cp Sender.jar:$2/* controller.NewCarsSender
 "
 
 2) Filterscript:
 
 "#!/bin/bash
 
  cd $1
  
  jar cfm Filter.jar ./src/main/resources/manifests/filter/META-INF/MANIFEST.MF -C build .
  
  java -Dorg.apache.activemq.SERIALIZABLE_PACKAGES=* -cp Filter.jar:$2/* controller.MessageFilterController "

 3) CarStandardizer:
 
 "#!/bin/bash
 
  cd $1
  
  jar cfm Consumer.jar ./src/main/resources/manifests/consumer/META-INF/MANIFEST.MF -C build .
  
  java -Dorg.apache.activemq.SERIALIZABLE_PACKAGES=* -cp Consumer.jar:$2/* controller.CarStandardizerController"
  
  * How to run:
  
  just write on terminal: 
   * ./NewCarsSender path1 path2
   * ./Filterscript path1 path2
   * ./CarStandardizer path1 path2
   
   where paht1 - it's path to the project folder, path2 - it's path to your libs.
   
   login "user", password "000"
   
# Running in the development environment

Clone ore download project from git.
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

 
 It will be run after the correct Class-Path.
 I used libraries jar files in the same folder as main jar files.
 
  #Enjoy!
 
 # <a name="author"></a>Author
 * [Taras Khalak](https://github.com/tarasulo)
@echo off
set JAVA_HOME=/Library/Java/JavaVirtualMachines/jdk1.8.0_301.jdk/Contents/Home/
set DERBY_HOME=/opt/derby/
set DERBY_SYSTEM_HOME=/Users/wojtek/PJATK/SEM 3/UTP/LAB/Derby/db_home/

@if "%DERBY_SYSTEM_HOME%" == "" goto noSystemHome
set LOCALCLASSPATH=%DERBY_HOME%/lib/derby.jar;%DERBY_HOME%/lib/derbynet.jar;%DERBY_HOME%/lib/derbyclient.jar;%DERBY_HOME%/lib/derbytools.jar
@if "%1" == "start" goto startServer
@if "%1" == "stop" goto stopServer
@if "%1" == "ij" goto ij
:ij
"%JAVA_HOME%\bin\java" -Dderby.system.home=%DERBY_SYSTEM_HOME% -cp "%LOCALCLASSPATH%;%CLASSPATH%" -Dij.protocol=jdbc:derby: org.apache.derby.tools.ij %2
goto end
:startServer
"%JAVA_HOME%\bin\java" -Dderby.system.home=%DERBY_SYSTEM_HOME% -cp "%LOCALCLASSPATH%;%CLASSPATH%" org.apache.derby.drda.NetworkServerControl start
goto end
:stopServer
"%JAVA_HOME%\bin\java" -Dderby.system.home=%DERBY_SYSTEM_HOME% -cp "%LOCALCLASSPATH%;%CLASSPATH%" org.apache.derby.drda.NetworkServerControl shutdown
goto end
:noSystemHome
echo DERBY_SYSTEM_HOME not set
:end

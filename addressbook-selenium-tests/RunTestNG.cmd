@echo off

set customSuite=testng-customsuite.xml
set runClass=org.testng.TestNG
set libDir=D:\software-testering.ru\tools\xstream-1.4.4\lib
set seleniumDir=D:\software-testering.ru\pft\tools
set logFile=results.log

java -cp bin;"%libDir%\xstream-1.4.4.jar";"%libDir%\xstream\xmlpull-1.1.3.1.jar";"%libDir%\xstream\xpp3_min-1.1.4c.jar";"%seleniumDir%\selenium-server-standalone-2.33.0.jar" %runClass% %customSuite% >> %logFIle%

pause
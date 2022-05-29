SET CURRENTDIR="%cd%"
echo %CURRENTDIR%

cd "%CURRENTDIR%/eureka-service"
call mvn clean install -Dmaven.test.skip=true

cd "%CURRENTDIR%/gateway-service"
call mvn clean install -Dmaven.test.skip=true

cd "%CURRENTDIR%/tweet-service"
call mvn clean install -Dmaven.test.skip=true

cd "%CURRENTDIR%/user-service"
call mvn clean install -Dmaven.test.skip=true

cd "%CURRENTDIR%/timeline-service"
call mvn clean install -Dmaven.test.skip=true

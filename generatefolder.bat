@echo off
:: Create root directory structure
:: mkdir "API-Gateway-with-Rate-Limiting"
:: cd "API-Gateway-with-Rate-Limiting"

:: Create root files
echo. > LICENSE
echo. > README.md
echo. > .gitignore
echo. > pom.xml
echo. > docker-compose.yml

:: Create Kubernetes folder and files
mkdir kubernetes
cd kubernetes
echo. > deployment.yaml
echo. > service.yaml
cd..

:: Create src folder structure
mkdir src\main
mkdir src\main\java
mkdir src\main\java\com
mkdir src\main\java\com\api
mkdir src\main\java\com\api\gateway
mkdir src\main\java\com\api\api
mkdir src\main\java\com\api\config
mkdir src\main\resources
mkdir src\main\test
mkdir src\main\test\java
mkdir src\main\test\java\com
mkdir src\main\test\java\com\api
mkdir src\main\test\java\com\api\gateway
mkdir src\main\test\resources

:: Create sample files
echo. > src\main\java\com\api\gateway\GatewayApplication.java
echo. > src\main\java\com\api\gateway\ApiGatewayConfig.java
echo. > src\main\java\com\api\gateway\RateLimitingFilter.java
echo. > src\main\java\com\api\gateway\AuthenticationFilter.java
echo. > src\main\java\com\api\gateway\CachingFilter.java
echo. > src\main\java\com\api\gateway\RedisConfig.java
echo. > src\main\java\com\api\api\ApiController.java
echo. > src\main\java\com\api\api\ApiRateLimitController.java
echo. > src\main\java\com\api\api\ApiDocsConfig.java
echo. > src\main\java\com\api\config\ApplicationConfig.java
echo. > src\main\resources\application.yml
echo. > src\main\resources\application-dev.yml
echo. > src\main\resources\logback-spring.xml
echo. > src\main\test\java\com\api\gateway\ApiGatewayTest.java
echo. > src\main\test\java\com\api\gateway\RateLimitingTest.java
echo. > src\main\test\java\com\api\gateway\ApiControllerTest.java

:: Done message
echo Project structure and files created successfully!
pause

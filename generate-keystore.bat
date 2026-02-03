@echo off
REM Generate self-signed keystore for HTTPS (run once, then enable SSL in application.properties)
set KEYSTORE=src\main\resources\keystore.p12
set ALIAS=workspaceservice
set PASSWORD=changeit
set VALIDITY=3650
set JAVA_HOME="C:\Program Files\Java\jdk-25\bin"
set PATH=%JAVA_HOME%;%PATH%

if exist "%KEYSTORE%" (
  echo Keystore already exists: %KEYSTORE%
  echo Delete it first if you want to regenerate.
  exit /b 0
)

keytool -genkeypair -alias %ALIAS% -storetype PKCS12 -keyalg RSA -keysize 2048 ^
  -keystore "%KEYSTORE%" -storepass %PASSWORD% -validity %VALIDITY% ^
  -dname "CN=localhost, OU=WorkspaceService, O=CapitalMarkets, L=City, ST=State, C=US"

if %ERRORLEVEL% equ 0 (
  echo.
  echo Keystore created: %KEYSTORE%
  echo Uncomment the server.ssl.* lines in src/main/resources/application.properties
  echo Then run the app - it will listen on https://localhost:8443
) else (
  echo keytool failed. Ensure JAVA_HOME is set and keytool is on PATH.
  exit /b 1
)

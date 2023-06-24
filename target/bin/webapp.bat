@REM ----------------------------------------------------------------------------
@REM  Copyright 2001-2006 The Apache Software Foundation.
@REM
@REM  Licensed under the Apache License, Version 2.0 (the "License");
@REM  you may not use this file except in compliance with the License.
@REM  You may obtain a copy of the License at
@REM
@REM       http://www.apache.org/licenses/LICENSE-2.0
@REM
@REM  Unless required by applicable law or agreed to in writing, software
@REM  distributed under the License is distributed on an "AS IS" BASIS,
@REM  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
@REM  See the License for the specific language governing permissions and
@REM  limitations under the License.
@REM ----------------------------------------------------------------------------
@REM
@REM   Copyright (c) 2001-2006 The Apache Software Foundation.  All rights
@REM   reserved.

@echo off

set ERROR_CODE=0

:init
@REM Decide how to startup depending on the version of windows

@REM -- Win98ME
if NOT "%OS%"=="Windows_NT" goto Win9xArg

@REM set local scope for the variables with windows NT shell
if "%OS%"=="Windows_NT" @setlocal

@REM -- 4NT shell
if "%eval[2+2]" == "4" goto 4NTArgs

@REM -- Regular WinNT shell
set CMD_LINE_ARGS=%*
goto WinNTGetScriptDir

@REM The 4NT Shell from jp software
:4NTArgs
set CMD_LINE_ARGS=%$
goto WinNTGetScriptDir

:Win9xArg
@REM Slurp the command line arguments.  This loop allows for an unlimited number
@REM of arguments (up to the command line limit, anyway).
set CMD_LINE_ARGS=
:Win9xApp
if %1a==a goto Win9xGetScriptDir
set CMD_LINE_ARGS=%CMD_LINE_ARGS% %1
shift
goto Win9xApp

:Win9xGetScriptDir
set SAVEDIR=%CD%
%0\
cd %0\..\.. 
set BASEDIR=%CD%
cd %SAVEDIR%
set SAVE_DIR=
goto repoSetup

:WinNTGetScriptDir
set BASEDIR=%~dp0\..

:repoSetup
set REPO=


if "%JAVACMD%"=="" set JAVACMD=java

if "%REPO%"=="" set REPO=%BASEDIR%\repo

set CLASSPATH="%BASEDIR%"\etc;"%REPO%"\org\apache\tomcat\embed\tomcat-embed-core\9.0.1\tomcat-embed-core-9.0.1.jar;"%REPO%"\org\apache\tomcat\tomcat-annotations-api\9.0.1\tomcat-annotations-api-9.0.1.jar;"%REPO%"\org\apache\tomcat\embed\tomcat-embed-jasper\9.0.1\tomcat-embed-jasper-9.0.1.jar;"%REPO%"\org\apache\tomcat\embed\tomcat-embed-el\9.0.1\tomcat-embed-el-9.0.1.jar;"%REPO%"\org\eclipse\jdt\ecj\3.12.3\ecj-3.12.3.jar;"%REPO%"\org\apache\tomcat\tomcat-jasper\9.0.1\tomcat-jasper-9.0.1.jar;"%REPO%"\org\apache\tomcat\tomcat-servlet-api\9.0.1\tomcat-servlet-api-9.0.1.jar;"%REPO%"\org\apache\tomcat\tomcat-juli\9.0.1\tomcat-juli-9.0.1.jar;"%REPO%"\org\apache\tomcat\tomcat-el-api\9.0.1\tomcat-el-api-9.0.1.jar;"%REPO%"\org\apache\tomcat\tomcat-api\9.0.1\tomcat-api-9.0.1.jar;"%REPO%"\org\apache\tomcat\tomcat-util-scan\9.0.1\tomcat-util-scan-9.0.1.jar;"%REPO%"\org\apache\tomcat\tomcat-util\9.0.1\tomcat-util-9.0.1.jar;"%REPO%"\org\apache\tomcat\tomcat-jasper-el\9.0.1\tomcat-jasper-el-9.0.1.jar;"%REPO%"\org\apache\tomcat\tomcat-jsp-api\9.0.1\tomcat-jsp-api-9.0.1.jar;"%REPO%"\javax\servlet\javax.servlet-api\4.0.1\javax.servlet-api-4.0.1.jar;"%REPO%"\com\google\oauth-client\google-oauth-client\1.30.4\google-oauth-client-1.30.4.jar;"%REPO%"\com\google\http-client\google-http-client\1.32.1\google-http-client-1.32.1.jar;"%REPO%"\org\apache\httpcomponents\httpclient\4.5.10\httpclient-4.5.10.jar;"%REPO%"\commons-logging\commons-logging\1.2\commons-logging-1.2.jar;"%REPO%"\org\apache\httpcomponents\httpcore\4.4.12\httpcore-4.4.12.jar;"%REPO%"\com\google\j2objc\j2objc-annotations\1.3\j2objc-annotations-1.3.jar;"%REPO%"\io\opencensus\opencensus-api\0.24.0\opencensus-api-0.24.0.jar;"%REPO%"\io\grpc\grpc-context\1.22.1\grpc-context-1.22.1.jar;"%REPO%"\io\opencensus\opencensus-contrib-http-util\0.24.0\opencensus-contrib-http-util-0.24.0.jar;"%REPO%"\com\google\code\findbugs\jsr305\3.0.2\jsr305-3.0.2.jar;"%REPO%"\com\google\guava\guava\28.1-android\guava-28.1-android.jar;"%REPO%"\com\google\guava\failureaccess\1.0.1\failureaccess-1.0.1.jar;"%REPO%"\com\google\guava\listenablefuture\9999.0-empty-to-avoid-conflict-with-guava\listenablefuture-9999.0-empty-to-avoid-conflict-with-guava.jar;"%REPO%"\org\checkerframework\checker-compat-qual\2.5.5\checker-compat-qual-2.5.5.jar;"%REPO%"\com\google\errorprone\error_prone_annotations\2.3.2\error_prone_annotations-2.3.2.jar;"%REPO%"\org\codehaus\mojo\animal-sniffer-annotations\1.18\animal-sniffer-annotations-1.18.jar;"%REPO%"\io\github\cdimascio\java-dotenv\5.2.2\java-dotenv-5.2.2.jar;"%REPO%"\org\jetbrains\kotlin\kotlin-stdlib\1.4.0\kotlin-stdlib-1.4.0.jar;"%REPO%"\org\jetbrains\kotlin\kotlin-stdlib-common\1.4.0\kotlin-stdlib-common-1.4.0.jar;"%REPO%"\org\jetbrains\annotations\13.0\annotations-13.0.jar;"%REPO%"\org\slf4j\slf4j-api\2.0.7\slf4j-api-2.0.7.jar;"%REPO%"\com\google\api-client\google-api-client-gson\2.2.0\google-api-client-gson-2.2.0.jar;"%REPO%"\com\google\api-client\google-api-client\2.2.0\google-api-client-2.2.0.jar;"%REPO%"\commons-codec\commons-codec\1.15\commons-codec-1.15.jar;"%REPO%"\com\google\http-client\google-http-client-apache-v2\1.42.3\google-http-client-apache-v2-1.42.3.jar;"%REPO%"\com\google\http-client\google-http-client-gson\1.42.3\google-http-client-gson-1.42.3.jar;"%REPO%"\com\google\code\gson\gson\2.10\gson-2.10.jar;"%REPO%"\com\auth0\java-jwt\4.4.0\java-jwt-4.4.0.jar;"%REPO%"\com\fasterxml\jackson\core\jackson-databind\2.14.2\jackson-databind-2.14.2.jar;"%REPO%"\com\github\xeroapi\xero-java\4.28.0\xero-java-4.28.0.jar;"%REPO%"\com\auth0\jwks-rsa\0.17.0\jwks-rsa-0.17.0.jar;"%REPO%"\com\googlecode\json-simple\json-simple\1.1.1\json-simple-1.1.1.jar;"%REPO%"\commons-io\commons-io\2.7\commons-io-2.7.jar;"%REPO%"\com\fasterxml\jackson\core\jackson-core\2.13.2\jackson-core-2.13.2.jar;"%REPO%"\com\fasterxml\jackson\core\jackson-annotations\2.13.2\jackson-annotations-2.13.2.jar;"%REPO%"\com\github\joschi\jackson\jackson-datatype-threetenbp\2.12.5\jackson-datatype-threetenbp-2.12.5.jar;"%REPO%"\org\threeten\threetenbp\1.5.1\threetenbp-1.5.1.jar;"%REPO%"\io\swagger\swagger-annotations\1.6.3\swagger-annotations-1.6.3.jar;"%REPO%"\org\glassfish\jersey\core\jersey-client\2.27\jersey-client-2.27.jar;"%REPO%"\javax\ws\rs\javax.ws.rs-api\2.1\javax.ws.rs-api-2.1.jar;"%REPO%"\org\glassfish\hk2\external\javax.inject\2.5.0-b42\javax.inject-2.5.0-b42.jar;"%REPO%"\org\glassfish\jersey\media\jersey-media-multipart\2.27\jersey-media-multipart-2.27.jar;"%REPO%"\org\jvnet\mimepull\mimepull\1.9.6\mimepull-1.9.6.jar;"%REPO%"\org\glassfish\jersey\media\jersey-media-json-jackson\2.27\jersey-media-json-jackson-2.27.jar;"%REPO%"\org\glassfish\jersey\ext\jersey-entity-filtering\2.27\jersey-entity-filtering-2.27.jar;"%REPO%"\com\fasterxml\jackson\module\jackson-module-jaxb-annotations\2.8.10\jackson-module-jaxb-annotations-2.8.10.jar;"%REPO%"\com\brsanthu\migbase64\2.2\migbase64-2.2.jar;"%REPO%"\com\channelape\shopify-sdk\2.8.0\shopify-sdk-2.8.0.jar;"%REPO%"\org\glassfish\jersey\core\jersey-common\2.25.1\jersey-common-2.25.1.jar;"%REPO%"\javax\annotation\javax.annotation-api\1.2\javax.annotation-api-1.2.jar;"%REPO%"\org\glassfish\jersey\bundles\repackaged\jersey-guava\2.25.1\jersey-guava-2.25.1.jar;"%REPO%"\org\glassfish\hk2\hk2-api\2.5.0-b32\hk2-api-2.5.0-b32.jar;"%REPO%"\org\glassfish\hk2\hk2-utils\2.5.0-b32\hk2-utils-2.5.0-b32.jar;"%REPO%"\org\glassfish\hk2\external\aopalliance-repackaged\2.5.0-b32\aopalliance-repackaged-2.5.0-b32.jar;"%REPO%"\org\glassfish\hk2\hk2-locator\2.5.0-b32\hk2-locator-2.5.0-b32.jar;"%REPO%"\org\javassist\javassist\3.20.0-GA\javassist-3.20.0-GA.jar;"%REPO%"\org\glassfish\hk2\osgi-resource-locator\1.0.1\osgi-resource-locator-1.0.1.jar;"%REPO%"\com\fasterxml\jackson\jaxrs\jackson-jaxrs-json-provider\2.4.0\jackson-jaxrs-json-provider-2.4.0.jar;"%REPO%"\com\fasterxml\jackson\jaxrs\jackson-jaxrs-base\2.4.0\jackson-jaxrs-base-2.4.0.jar;"%REPO%"\org\glassfish\jersey\media\jersey-media-jaxb\2.25.1\jersey-media-jaxb-2.25.1.jar;"%REPO%"\org\apache\commons\commons-lang3\3.4\commons-lang3-3.4.jar;"%REPO%"\joda-time\joda-time\2.9.9\joda-time-2.9.9.jar;"%REPO%"\org\jscience\jscience\4.3.1\jscience-4.3.1.jar;"%REPO%"\org\javolution\javolution\5.2.3\javolution-5.2.3.jar;"%REPO%"\org\json\json\20160810\json-20160810.jar;"%REPO%"\org\slf4j\log4j-over-slf4j\1.7.22\log4j-over-slf4j-1.7.22.jar;"%REPO%"\com\github\rholder\guava-retrying\2.0.0\guava-retrying-2.0.0.jar;"%REPO%"\javax\xml\bind\jaxb-api\2.3.0\jaxb-api-2.3.0.jar;"%REPO%"\com\sun\xml\bind\jaxb-core\2.3.0\jaxb-core-2.3.0.jar;"%REPO%"\com\sun\xml\bind\jaxb-impl\2.3.0\jaxb-impl-2.3.0.jar;"%REPO%"\javax\activation\activation\1.1.1\activation-1.1.1.jar;"%REPO%"\org\mockito\mockito-inline\5.2.0\mockito-inline-5.2.0.jar;"%REPO%"\org\mockito\mockito-core\5.2.0\mockito-core-5.2.0.jar;"%REPO%"\net\bytebuddy\byte-buddy\1.14.1\byte-buddy-1.14.1.jar;"%REPO%"\net\bytebuddy\byte-buddy-agent\1.14.1\byte-buddy-agent-1.14.1.jar;"%REPO%"\org\objenesis\objenesis\3.3\objenesis-3.3.jar;"%REPO%"\io\github\cdimascio\dotenv-kotlin\6.4.1\dotenv-kotlin-6.4.1.jar;"%REPO%"\io\github\cdimascio\dotenv-java\2.3.2\dotenv-java-2.3.2.jar;"%REPO%"\nz\vortus\AdminApplication\1.0-SNAPSHOT\AdminApplication-1.0-SNAPSHOT.jar

set ENDORSED_DIR=
if NOT "%ENDORSED_DIR%" == "" set CLASSPATH="%BASEDIR%"\%ENDORSED_DIR%\*;%CLASSPATH%

if NOT "%CLASSPATH_PREFIX%" == "" set CLASSPATH=%CLASSPATH_PREFIX%;%CLASSPATH%

@REM Reaching here means variables are defined and arguments have been captured
:endInit

%JAVACMD% %JAVA_OPTS%  -classpath %CLASSPATH% -Dapp.name="webapp" -Dapp.repo="%REPO%" -Dapp.home="%BASEDIR%" -Dbasedir="%BASEDIR%" launch.Main %CMD_LINE_ARGS%
if %ERRORLEVEL% NEQ 0 goto error
goto end

:error
if "%OS%"=="Windows_NT" @endlocal
set ERROR_CODE=%ERRORLEVEL%

:end
@REM set local scope for the variables with windows NT shell
if "%OS%"=="Windows_NT" goto endNT

@REM For old DOS remove the set variables from ENV - we assume they were not set
@REM before we started - at least we don't leave any baggage around
set CMD_LINE_ARGS=
goto postExec

:endNT
@REM If error code is set to 1 then the endlocal was done already in :error.
if %ERROR_CODE% EQU 0 @endlocal


:postExec

if "%FORCE_EXIT_ON_ERROR%" == "on" (
  if %ERROR_CODE% NEQ 0 exit %ERROR_CODE%
)

exit /B %ERROR_CODE%

 @echo off
 setlocal enabledelayedexpansion
 set JAVA=".\jre\bin\javaw"
 set OPTS=-Xms128M -Xmx512M
 set LIBPATH=.\lib
 set CONFIG=.
 set CP=%CONFIG%;./EasyPhoto.jar;
 set MAIN=org.shaitu.easyphoto.Startup
 
 for /f %%i in ('dir /b %LIBPATH%\*.jar^|sort') do (
	set CP=!CP!%LIBPATH%\%%i;
 )
                
start "EasyPhoto" %JAVA% %OPTS% -cp %CP% %MAIN%   
set curpath=%~dp0
%~d0
cd %curpath%

rem main ���Լ�
java -cp lib/junit-4.10.jar;./bin/ junit.samples.AllTests

pause
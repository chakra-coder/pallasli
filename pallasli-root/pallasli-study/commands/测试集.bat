set curpath=%~dp0
%~d0
cd %curpath%

rem main ���Լ�
java -cp lib/junit-4.10.jar;./bin/ junit.samples.AllTests

rem junit ע�����
java -cp lib/junit-4.10.jar;./bin/ org.junit.runner.JUnitCore  com.lyt.service.SuiteTest

pause
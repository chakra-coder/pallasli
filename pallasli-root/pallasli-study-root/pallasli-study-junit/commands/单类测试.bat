set curpath=%~dp0
%~d0
cd %curpath%

rem junit ע�����
java -cp lib/junit-4.10.jar;./bin/ org.junit.runner.JUnitCore  com.lyt.service.CalculatorParameterizedTest
java -cp lib/junit-4.10.jar;./bin/ org.junit.runner.JUnitCore  com.lyt.service.CalculatorTest

pause
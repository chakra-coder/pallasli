
set curpath=%~dp0
%~d0
cd %curpath%

echo clean ����Ŀ¼target/
echo resources
echo compile ����target/classesĿ¼
echo testResources
echo testCompile  
call mvn test -DfailIfNoTests=false
rem call mvn test -DfailIfNoTests=false������������
call mvn test -Dtest=TestAccountCaptchaServiceImp,TestRandomGenerator
 pause

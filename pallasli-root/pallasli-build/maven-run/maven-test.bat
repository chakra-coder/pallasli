
set curpath=%~dp0
%~d0
cd %curpath%

echo clean ����Ŀ¼target/
echo resources
echo compile ����target/classesĿ¼
echo testResources
echo testCompile  


call mvn clean test
 
 pause

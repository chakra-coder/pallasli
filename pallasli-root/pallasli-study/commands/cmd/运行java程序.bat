rem java������������Java������������Java�������Java�����������ص��࣬Ȼ�����������main()������
rem -classpath ���趨Ҫ���������·����������Ŀ¼��jar�ļ���zip�ļ������涼��class�ļ������Ḳ�ǵ����е�CLASSPATH���趨������á�;���ָ���
rem -verbose   ���������ʱ��ϸ������Ϣ��
rem -jar       ��ָ����ִ�е�jar����

 

��/testĿ¼�����б�����Test����


set tmp_cur=%~dp0
if not "%JAVA_HOME%" == "" goto gotJava_home
set JAVA_HOME=D:\jdk7
:gotJava_home 
%JAVA_HOME%\bin\java -classpath ./classes Test
#!/bin/sh
mainclass='org.shaitu.easyphoto.Startup'
varpid=`ps -ef|grep $mainclass |grep -v grep|awk '{print $2}'`
jarlib=`ls lib/*.jar | tr '\n' ':'`
classpath='.:./EasyPhoto.jar:'${jarlib}
jvmarg='-Xmx512m -Xms128m'
java $jvmarg -classpath ${classpath} ${mainclass} &


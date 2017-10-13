#!/bin/bash
JAVA_OPTS="$JAVA_OPTS -server -XX:+PrintGCDateStamps
    -XX:+PrintGCTimeStamps \
    -XX:+PrintGCDetails \
    -XX:+PrintTenuringDistribution \
    -Xloggc:$SERVER_HOME/logs/gc.log \
    -Xdebug -Xrunjdwp:transport=dt_socket,address=8887,server=y,suspend=n  
    -Xmx1024M -Xms1024M -XX:PermSize=250M -XX:MaxPermSize=250M -Xss256K"


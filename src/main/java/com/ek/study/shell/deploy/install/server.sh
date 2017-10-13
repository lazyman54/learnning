#!/bin/sh

# -----------------------------------------------------------------------------
# boot Server 启动脚本
#
#   不要在这里修改环境变量。这些变量可以放在 $BOOT_HOME/env/java_env.sh 中，
#   以分离定制参数.
#
#   SERVER_HOME     server服务的根目录
#
#   JAVA_HOME       Java 安装路径 
#
#   JAVA_OPTS       Java 虚拟机参数，例如堆大小参数
#
#   JPDA_ADDRESS    当使用 "debug start"时，指定的jdpa调试端口,默认8383
#
#   JPDA_SUSPEND    当使用"jpda start"启动时，指定JVM是否在启动后马上挂起
#                   默认不挂起，是"n"， 否则请指定y，启动就挂起.
#
#   SERVER_PID      后台启动时，存放进程的PID
#
#   SERVER_JAR      要启动的服务文件
#
# -----------------------------------------------------------------------------
PRG="$0"

# resolve links - $0 may be a softlink
while [ -h "$PRG" ]; do
  ls=`ls -ld "$PRG"`
  link=`expr "$ls" : '.*-> \(.*\)$'`
  if expr "$link" : '/.*' > /dev/null; then
    PRG="$link"
  else
    PRG=`dirname "$PRG"`/"$link"
  fi
done

PRGDIR=`dirname "$PRG"`
[ -z "$SERVER_HOME" ] && SERVER_HOME=`cd "$PRGDIR/.." >/dev/null; pwd`

if [ -z "$JAVA_HOME" ];then
    echo "# 系统未检测到java home（JAVA_HOME），尝试java基础命令是否可以执行... #"
    if ! command -v jps >/dev/null 2>&1; then
        echo "# 系统检测jps命令无法执行。。。。。请检查 #"
        exit 3
    elif ! command -v java >/dev/null 2>&1; then
        echo "# 系统检测java命令无法执行。。。。，请检查后重试"
        exit 3
    fi
    direct=true
fi

# auto read jar name
app_name=`ls "$SERVER_HOME/jar" `
SERVER_JAR="$SERVER_HOME/jar/$app_name"

if [ -r "$SERVER_HOME/env/java_env.sh" ]; then
  . "$SERVER_HOME/env/java_env.sh"
fi
if [ -r "$SERVER_HOME/env/config.sh" ]; then
  . "$SERVER_HOME/env/config.sh"
fi

# the pid of the java program
pid=0

checkPidByJps(){
    if test -z "$direct";then
        javaps=`$JAVA_HOME/bin/jps | grep $app_name`
    else
        javaps=$(jps | grep $app_name)
    fi
    if [ -n "$javaps" ]; then
        pid=`echo $javaps | awk '{print $1}'`
    else 
        pid=0
    fi
}
# this fun is not easy to unstand, not recommend, see checkPidByJps
checkPidByFile(){
  if [ -n "$SERVER_PID" ]; then
    if [ -f "$SERVER_PID" ]; then
      if [ -s "$SERVER_PID" ]; then 
        echo "Existing PID file found during start."
        if [ -r "$SERVER_PID" ]; then
          PID=`cat "$SERVER_PID"`
          ps -p $PID >/dev/null 2>&1
          if [ $? -eq 0 ]; then
            echo "Server appears to still be running with PID $PID. Start aborted."
            exit 1
          else 
            echo "Removing/clearing stale PID file."
            rm -rf "$SERVER_PID" >/dev/null 2>&1
            if [ $? != 0 ]; then
              if [ -w "$SERVER_PID" ]; then 
                cat /dev/null > "$SERVER_PID"
              else
                echo "Unable to remove or clear stale PID file. Start aborted."
                exit 1
              fi
            fi
          fi
        else 
	  echo "unable to read pid file. start abort"
          exit 1
        fi
      else
        rm -f "$KIEV_PID" >/dev/null 2>&1
        if [ $? != 0 ]; then
          if [ ! -w "$KIEV_PID" ]; then
            echo "Unable to remove or write to empty PID file. Start aborted."
            exit 1
          fi
        fi
      fi
    fi
  fi
}

doStartServer(){
  
  local nohup_log=/dev/null  
  if [ -n "$NOHUP_LOG" ]; then
     nohup_log=$NOHUP_LOG
     local nohupdir=$(dirname $nohup_log)
     test -d "$nohupdir" || mkdir -p "$nohupdir"
  fi
  checkPidByJps
  if [ "$pid" -ne 0 ]; then
     echo "================================"
     echo "warn: $SERVER_JAR already started! (pid=$pid)"
     echo "================================"
  else
     echo "===================================start begin=================================="
     echo "**************ready to start your server $SERVER_JAR. in background*************" 
     echo "nohup dir:  $nohup_log"
     if [ -z "$direct" ];then
        eval $JAVA_HOME/bin/java $JAVA_OPTS \
            -Denv=\"$ENV\" \
            -jar $SERVER_JAR > $nohup_log 2>&1 "&"
     else
        eval java $JAVA_OPTS \
            -Denv=\"$ENV\" \
            -jar $SERVER_JAR > $nohup_log 2>&1 "&"
     fi
  fi

  if [ -n "$SERVER_PID" ]; then
     echo $! > "$SERVER_PID"
  else
     echo "there is no file to write pid to."
  fi

  echo "PID":`cat "$SERVER_PID"`
  echo "===================================start   end=================================="
      #java -jar -Denv=$ENV $JVMARGS $FULLPATH/$JAR  1>/dev/null 2>$FULLPATH/error.log &
}

doStopServer(){
  echo "===================================stop  begin=================================="
  checkPidByJps
  if [ $pid -ne 0 ]; then
     local tryTime=5
     while [ $tryTime -gt 0 ]; do
        kill -15 $pid >/dev/null 2>&1
        if [ $? -gt 0 ]; then 
           cat /dev/null > $SERVER_PID
           echo "****Server Stopped****"
	   break
        fi
        if [ $tryTime -gt 0 ]; then
           echo -e ".\c"
	   sleep 1
	fi
	if [ $tryTime -eq 0 ]; then
	   echo "Server stop fail, try latter."
	fi
	tryTime=`expr $tryTime - 1`
     done
   else
      echo "*************Server has already been stopped****************"
   fi
   echo "===================================stop    end=================================="
}
doStatus(){
   checkPidByJps
 
   if [ $pid -ne 0 ];  then
      echo "$SERVER_JAR is running! (pid=$pid)"
   else
      echo "$SERVER_JAR is not running"
   fi
}
case "$1" in
   'start')
     doStartServer
   ;;
   'stop')
     doStopServer
   ;;
   'restart')
     doStopServer
     doStartServer
   ;;
   'status')
     doStatus
   ;;
   *)
     echo "Usage: $0 {start|stop|restart|status}"
esac


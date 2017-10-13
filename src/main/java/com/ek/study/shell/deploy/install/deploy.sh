#!/bin/bash

curDir=$(dirname $0)

refresh(){
    local filename_bak="repository.sh.bak"
    if [ -e ${filedir}/${filename} ];then
        mv ${filedir}/${filename} ${filedir}/${filename_bak}
    fi
    scp root@10.8.15.82:/data/deploy/repository.sh ${filedir}/${filename} &
    local wtime=0
    while (($wtime<=5));do
        echo -e ".\c"
        sleep 1
        test -e "${filedir}/${filename}"
        if [ $? -eq 0 ]; then
            break
        fi
        let wtime++
    done
    if [ wtime -eq 6 ];then
        echo "refresh conf file fail, please check remote server data:10.8.15.82:/data/deploy/conf.sh"
        exit 1
    fi
    rm -rf ${filedir}/${filename_bak}
}

check_param(){
    if [ $# -eq 0 ];then
        if [ -z $app ];then
            echo "you need at least tell me the server name....."
            exit 1
        elif [ -z $dir ];then
            dir=$curDir
        fi
    elif [ $# -eq 1 ];then 
        echo "use current dir as install dir"
        if [ -z $dir ];then
            dir=$curDir
        fi
        app=$1
    elif [ $# -ge 2 ];then
        dir=$2
        app=$1
    fi
    if [ ! -d ${dir}/jar ] || [ ! -d ${dir}/bak ] || [ ! -d ${dir}/bin ];then
        echo "dir seems not like to be legal......."
        exit 1
    fi
    echo "*********************app dir : $dir *******************"
    echo "*********************app name: $app *******************"
}

function fetch(){
   echo -e "**************try to fetch jar from server [${remoteDir}] please wait...**************"
   scp ${remoteDir}/$app  ${dir}/jar/ &
   local waittime=0
   while (($waittime<=20));do
      echo -e ".\c"
      sleep 1
      if [ -e ${dir}/jar/$app ];then
         break
      fi
      let waittime++
      echo -e "$waittime \c"
   done
   if [ $waittime -eq 21 ]; then
      echo "fetch jar from server[ $remoteDir ] fail....please check and try later"
      mv ${dir}/bak/${app}.bak ${dir}/jar/${app}
      exit 1
   fi
}

# 参数是要检测的app名字
function checkPro(){
   local val=$(ps --no-heading -C java -f --width 1000 | grep $1 | awk '{print $2}' | wc -l)
   return $val
}

# 参数是服务所在的根目录
function doStart(){
   $1/bin/server.sh start
}
# 参数是服务所在的根目录
function doRestart(){
   $1/bin/server.sh restart
}

filedir="/data/app"
filename="repository.sh"

if [ -r ${filedir}/${filename} ];then
    . ${filedir}/${filename}
fi
if [ -r "$curDir/conf.sh" ]; then
    . $curDir/conf.sh
fi
if [ -n "$1" ] && [ "$1" == "refresh" ];then
    refresh
    exit 0
fi
check_param $*
if [ -e "${dir}/jar/${app}" ];then
    mv ${dir}/jar/$app ${dir}/bak/${app}.bak
fi
fetch $dir $app
 
checkPro $app
isServerRunning=$?
if [ $isServerRunning -gt 0 ]; then
    echo "************server has already run, now try to restart your server:[${app}]*********"
    doRestart $dir
else
    echo "***********server has not run, now try to start your server: [${app}]***************"
    doStart $dir
fi

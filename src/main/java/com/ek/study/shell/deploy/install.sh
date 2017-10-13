#!/bin/bash
#############################################################################
# 远程服务器请先下载本文件并运行此脚本，来安装服务部署目录                  #
# 第一个参数：project name，对应Jenkins上的构建模块名（必填）               #
# 第二个参数：module name，要部署的项目名字，project下可有多个module（必填）#
# 第三个参数：dir，部署程序所在的目录，默认用/data/app（可选）              #
# 第四个参数：app，要部署的jar包的名字，可后续填写（可选）                  #
#############################################################################

curdir=$(dirname $0)
dir="/data/app"
app="please enter jar name"
echo "#####################################################################################"
echo "# begin to install #"
if [ -z "$1" ];then
    echo "# please enter your project name, see also your jenkins module name #"
    exit 1
fi
if [ -z "$2" ];then
    echo "# please enter you module name, see also your artifactId #"
    exit 1
fi
project=$1
module=$2
if test -n "$3"; then
    dir=$3
fi
if [ -n "$4" ];then
    app=$4
fi
parentdir=${dir}/${project}
moduledir="${parentdir}/${module}"
if test ! -d "${moduledir}"; then
    mkdir -p "${moduledir}"
fi
download(){
    echo "# try to install $1 into dir $2 #"
    scp root@10.8.15.82:/data/deploy/install/$1 $2 &
    local wtime=0
    while (($wtime<=5)); do
        echo -e ".\c"
        sleep 1
        if test -e $2/$1; then
            break
        fi
    let wtime++
    done
    if [ $wtime -eq 6 ]; then
        echo "# download file $1 fail, please check and try later. #"
        exit 2
    fi
}
dirbulid(){
    if [ ! -d "${moduledir}/bak" ];then
        mkdir -p "${moduledir}/bak"
    fi
    if [ ! -d "${moduledir}/bin" ];then
        mkdir -p "${moduledir}/bin"
    fi
    if [ ! -d ${moduledir}/env ];then
        mkdir -p ${moduledir}/env
    fi
    if [ ! -d ${moduledir}/jar ];then
        mkdir -p ${moduledir}/jar
    fi
    if [ ! -d ${moduledir}/logs ];then
        mkdir -p ${moduledir}/logs
    fi
}
makeconf(){
local conf="${moduledir}/conf.sh"
if [ -d "$conf" ];then
    > "$conf"
fi

echo "project=\"${project}\"" >> "$conf"
echo "module=\"${module}\"" >> "$conf"
echo "remotehost=\"root@10.8.15.82\"" >> "$conf"
echo "dir=\"${moduledir}\"" >> "$conf"
echo "app=\"${app}\"" >> "$conf"
echo 'remoteDir="${remotehost}:${repository}/${project}/${module}/target"' >> "$conf"
}
dirbulid
download "repository.sh" "${dir}"
download "server.sh" "${moduledir}/bin"
download "java_env.sh" "${moduledir}/env"
download "config.sh" $moduledir/env
# download "conf.sh" $moduledir
makeconf
download "deploy.sh" $moduledir
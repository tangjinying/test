FROM java:8

MAINTAINER tjy

ADD test111.jar test111.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","/test111.jar"]


#!/bin/bash
# 服务名称
# author yhw
server_name=test111
port=8080

echo "判断容器是否存在"
#判断，-n检测字符串长度是否不为0，不为0则为true,-q:仅仅列出容器ID，-f:使用过滤器,-a列出所有容器
if [[ -n $(docker ps -a -q -f "name=$server_name") ]];
then
    echo "容器已存在"
    echo "强制删除$server_name容器"
    # 删除容器
    docker rm -f $server_name
    echo "删除$server_name镜像"
    # 删除镜像
    docker rmi $server_name:1.0
    echo "打包镜像"
    # 打包镜像
    docker build -t $server_name:1.0 .
    echo "创建容器"
    docker run -p $port:$port -d --name=$server_name $server_name:1.0
	echo "容器启动成功"
else
    echo "打包镜像"
    # 打包镜像
    docker build -f ./docker/Dockerfile -t $server_name:1.0 .
    echo "创建容器"
    docker run -p $port:$port -d --name=$server_name $server_name:1.0
	echo "容器启动成功"
fi

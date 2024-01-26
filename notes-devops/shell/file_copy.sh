#!/bin/bash

# 文件分发shell脚本
# 这个脚本不会自动输入密码

filepath=$1
remote_filepath=$2

while read line;do
    ip=`echo $line | cut -d " " -f1`             # 提取文件中的ip
    user_name=`echo $line | cut -d " " -f2`      # 提取文件中的用户名

    echo "准备拷贝文件到机器$ip, 目标用户$user_name"
    scp -oStrictHostKeyChecking=no $filepath $user_name@$ip:$remote_filepath
    echo "${ip}处理结束"

done < ./host_ip.txt      # 读取存储ip的文件


# 调用说明：
# bash file_copy.sh 要复制的文件地址 远程服务器的地址
# 比如
# bash file_copy.sh ./hosts /etc/hosts
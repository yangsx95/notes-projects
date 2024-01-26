#!/bin/bash

# 文件分发shell脚本

filepath=$1
remote_filepath=$2

while read line;do
    ip=`echo $line | cut -d " " -f1`             # 提取文件中的ip
    user_name=`echo $line | cut -d " " -f2`      # 提取文件中的用户名
    pass_word=`echo $line | cut -d " " -f3`      # 提取文件中的密码

    echo "准备拷贝文件到机器$ip, 目标用户$user_name, 密码 $pass_word"
    # 进入expect环境
    expect <<EOF
        spawn scp -oStrictHostKeyChecking=no $filepath $user_name@$ip:~
        expect {
                # expect 实现自动输入密码
                "password" { send "$pass_word\n"}
        }
        expect eof
EOF
    echo "${ip}处理结束"
done < ./host_ip.txt      # 读取存储ip的文件


# 调用说明：
# bash file_push.sh 要复制的文件地址 远程服务器的地址
# 比如
# bash file_push.sh ./a.txt ~
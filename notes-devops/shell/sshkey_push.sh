#!/bin/bash

# 这是一个秘钥分发的脚本
# 需要配置文件 host_ip.txt
# 当前机器如果密钥对不存在则创建

rsa_pub=~/.ssh/id_rsa.pub

[ ! -f ${rsa_pub} ] && ssh-keygen -t rsa -P ''

while read line;do
        ip=`echo $line | cut -d " " -f1`             # 提取文件中的ip
        user_name=`echo $line | cut -d " " -f2`      # 提取文件中的用户名
        pass_word=`echo $line | cut -d " " -f3`      # 提取文件中的密码
expect <<EOF
        # 复制公钥到目标主机
        spawn ssh-copy-id -i ${rsa_pub} $user_name@$ip
        expect {
                # expect 实现自动输入密码
                "yes/no" { send "yes\n";exp_continue}
                "password" { send "$pass_word\n"}
        }
        expect eof
EOF

done < ./host_ip.txt      # 读取存储ip的文件

# pscp.pssh -h /root/host_ip.txt /root/your_scripts.sh /root     # 推送你在目标主机进行的部署配置
# pssh -h /root/host_ip.txt -i bash /root/your_scripts.sh        # 进行远程配置，执行你的配置脚本
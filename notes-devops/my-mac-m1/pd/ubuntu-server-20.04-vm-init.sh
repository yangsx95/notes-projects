#!/bin/bash

#***********************************************
# Author:        yangsx
# Args:          $1 hostname 当前主机的主机名
#                $2 ipaddr   当前主机的ip
#                $3 gateway  当前主机的网关
# Version:       1.0
# Date:          2022-08-12 09:59:51
# FileName:      ubuntu-server-20.04-vm-init
# Description:   虚拟机ubuntu20.04初始化脚本
# Usage:         sudo bash ubuntu-server-20.04-vm-init.sh k8s-master 192.168.12.100 192.168.12.1
#                需要使用ansible分发脚本然后批量执行
#***********************************************

HOSTNAME=$1
IPADDR=$2
GATEWAT=$3

echo "脚本准备执行，目标信息:"
echo `cat /proc/version`
echo "HOSTNAME = $HOSTNAME
IPADDR = $IPADDR
GATEWAT = $GATEWAT
"

sleep 1;

function processStaticIp () {
    # 获取en网卡
    NET_EN=""
    for net in `ls /sys/class/net`; do
        if [[ "${net}" == *"en"* ]]; then
           variable='value'
            NET_EN=$net
        fi
    done

    if [[ -z "${NET_EN}" ]]; then
        echo "未找到en开头的网卡,静态ip不处理"
        return 1
    fi
    
    echo "找到目标网卡 ${NET_EN},准备进行静态IP处理"
    
    for confname in `ls /etc/netplan/ |  egrep -v  "(\.bak$)"`; do
        echo "准备处理配置 ${confname}"
        # 处理配置文件
         cp "/etc/netplan/${confname}" "/etc/netplan/${confname}.bak"
         echo "network:
    ethernets:
        ${NET_EN}:
            addresses: [${IPADDR}/24]
            gateway4: $GATEWAT
            nameservers:
                addresses:
                - 8.8.8.8
    version: 2
" > "/etc/netplan/${confname}"
    done

     netplan apply
    
    return 0
}

# 20.04升级
apt upgrade

apt update

# 准备所有软件包
# passwd, expect -> 用于修改密码，切换root用户
apt install passwd expect ntpdate

# 移除无用软件包
apt autoremove

# 修改root用户的密码
echo "root:root" | chpasswd

# 安装bash-complete
apt install bash-completion

# 修改hostname
echo $HOSTNAME > /etc/hostname

# 关闭防火墙
ufw disable

# 关闭selinux(20.04最新默认关闭)
setenforce 0

# 关闭swap
swapoff -a
sed -ri 's/.*swap.*/#&/' /etc/fstab   # 永久

# 配置ssh root用户远程登录
cp /etc/ssh/sshd_config /etc/ssh/sshd_config_bak
sed "s/^#(PermitRootLogin\s).*/\1yes/g" /etc/ssh/sshd_config -i -r
systemctl restart sshd

# 配置时钟同步
ntpdate cn.pool.ntp.org

# 配置静态IP地址
processStaticIp

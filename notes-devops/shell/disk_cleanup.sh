#!/bin/bash

#***********************************************
#Author:        yangsx
#Args:
#Version:       1.0
#Date:          2022-08-03 09:45:51
#FileName:      disk-cleanup
#Description:   磁盘超过百分之60清理系统日志文件
#***********************************************

set -e

used=$(df -h / | awk '{print $5}' | tail -1 | sed 's/%//g')
if [ "$used" -gt 60 ]; then
  cd /var/log
  rm_system_log_file=(messages-* cron-* btmp-* maillog-* secure-* spooler-*)
  for i in "${rm_system_log_file[@]}"; do
    echo "#### start to rm ${i} #####"
    rm -f "$i"
    echo "#### end to rm ${i} #####"
  done
fi
#!/bin/bash

#***********************************************
# Author:        yangsx
# Args:          $1 pattern 文件路径匹配模板
#                $2 retain 保留文件数量
# Version:       1.0
# Date:          2022-08-12 09:59:51
# FileName:      file-cleanup
# Description:   根据路径pattern找到文件，保留最新的n
#                个文件，删除其他所有文件。
# Usage:         bash ./file-clean.sh xxx/nacos/*.log 3
#                删除nacos日志路径下的所有log日志文件，
#                保留最新的3个
#***********************************************

# set -v 

files=($@)
let "filesize=${#files[@]}-1"
count="${files[$filesize]}"

echo "要处理的文件数量为$filesize 保留的最新的文件数量 $count"
let "target_size=$filesize-$count"
echo "准备开始执行，将会删除${target_size}个较早的文件"

unset "files[$filesize]"

echo "${files[@]}" | xargs ls -1 -t | tail -$target_size | awk '{print "rm -f "$1}' | bash
#!/bin/bash

startdate=$1
enddate=$2
git log --since=$startdate --until==$enddate --pretty=tformat: --numstat | awk '{ add += $1; subs += $2; loc += $1 - $2 } END { printf "added lines: %s, removed lines: %s, total lines: %s\n", add, subs, loc }'
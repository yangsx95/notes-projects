#!/bin/sh

API_NAME=$2
JAR_NAME=$API_NAME\.jar

JVM_OPTIONS="-Xms8192m -Xmx8192m -XX:+UseG1GC -XX:MaxGCPauseMillis=20 -XX:+PrintGCDetails -XX:+PrintStringTableStatistics -XX:+PrintSafepointStatistics -XX:+PrintGCApplicationStoppedTime -Xloggc:/opt/module/easyreport/logs/gc-%p-%t.log -XX:HeapDumpPath=/opt/module/easyreport/logs"

PID=$API_NAME\.pid

usage() {
  echo "Usage: sh 执行脚本.sh [start|stop|restart|status]"
  exit 1
}

is_exist() {
  pid=$(ps -ef | grep "$JAR_NAME" | grep -v grep | awk '{print $2}')
  if [ -z "${pid}" ]; then
    return 1
  else
    return 0
  fi
}

start() {
  is_exist
  if [ $? -eq "0" ]; then
    echo ">>> ${JAR_NAME} is already running PID=${pid} <<<"
  else
    nohup java -jar ${JVM_OPTIONS} "$JAR_NAME" >"$API_NAME".log 2>&1 &
    echo $! >"$PID"
    echo ">>> start $JAR_NAME success PID=$! <<<"
  fi
}

stop() {
  pidf=$(cat $PID)
  echo ">>> api PID = $pidf begin kill $pidf <<<"
  kill $pidf
  rm -rf $PID
  sleep 2
  is_exist
  if [ $? -eq "0" ]; then
    echo ">>> api 2 PID = $pid begin kill -9 $pid  <<<"
    kill -9 $pid
    sleep 2
    echo ">>> $JAR_NAME process stopped <<<"
  else
    echo ">>> ${JAR_NAME} is not running <<<"
  fi
}

status() {
  is_exist
  if [ $? -eq "0" ]; then
    echo ">>> ${JAR_NAME} is running PID is ${pid} <<<"
  else
    echo ">>> ${JAR_NAME} is not running <<<"
  fi
}

restart() {
  stop
  start
}

case "$1" in
"start")
  start
  ;;
"stop")
  stop
  ;;
"status")
  status
  ;;
"restart")
  restart
  ;;
*)
  usage
  ;;
esac
exit 0
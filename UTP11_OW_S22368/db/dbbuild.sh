#!/bin/zsh

DERBY_HOME="/opt/derby"

arg=$1

case $arg in

  "start")
    java -jar "$DERBY_HOME/lib/derbynet.jar" start && echo "database started" && exit 0;
    ;;

  "load")
    java -jar "$DERBY_HOME/lib/derbyrun.jar" ij $2 && echo "database loaded from $2" && exit 0;
    ;;

  "stop")
    java -jar "$DERBY_HOME/lib/derbynet.jar" start && echo "database stopped" && exit 0;
    ;;
esac

echo "something went wrong";
exit 1;

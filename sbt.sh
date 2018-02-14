#!/bin/bash
args=(`echo $@`)

SBT_OPTS="-Dfile.encoding=UTF-8 -Duser.timezone=JST -Xms512M -Xmx1536M -Xss1M -XX:+CMSClassUnloadingEnabled"
DEBUG="-Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=5005"
COMMAND="${args[@]}"

java $SBT_OPTS $DEBUG -jar `dirname $0`/sbt-launch-1.1.0.jar $COMMAND; exit $?

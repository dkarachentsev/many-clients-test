#!/bin/bash
now0=`date +'%d%m-%H%M%S'`
if [ "$JAVA_HOME" = "" ]; then
    JAVA=`type -p java`
    RETCODE=$?

    if [ $RETCODE -ne 0 ]; then
        echo "JAVA_HOME environment variable is not found."

        exit 1
    fi
else
    JAVA=${JAVA_HOME}/bin/java
fi

export IGNITE_WORK_DIR=`pwd`/work

mkdir -p $IGNITE_WORK_DIR

dstat -t --top-mem -m -s -g -d --fs --top-io 3 > ./work/dstat-$now0.log 2>&1 &

rm -rf $IGNITE_WORK_DIR

ulimit -n 4096

for ((i=1;i<=$2;i++))
do
    $JAVA -Xms2g -Xmx4g -XX:+HeapDumpOnOutOfMemoryError -Denv=$1 \
        -XX:+UnlockCommercialFeatures -XX:+FlightRecorder \
        -XX:StartFlightRecording=duration=120s,delay=210s,filename=$IGNITE_WORK_DIR/server.jfr \
        -Xloggc:./gc${i}.log -XX:+PrintGCDetails \
        -verbose:gc -XX:+UseParNewGC -XX:+UseConcMarkSweepGC \
        -DIGNITE_WORK_DIR=$IGNITE_WORK_DIR -DIGNITE_UPDATE_NOTIFIER=false \
        -cp target/many-clients-test-1.0-SNAPSHOT-jar-with-dependencies.jar Server > /dev/null &
done

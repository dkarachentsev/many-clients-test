#!/bin/bash

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

rm -rf $IGNITE_WORK_DIR

$JAVA -Xms4g -Xmx4g -Denv=$1 -DIGNITE_WORK_DIR=$IGNITE_WORK_DIR -DIGNITE_QUIET=false \
    -cp target/many-clients-test-1.0-SNAPSHOT-jar-with-dependencies.jar Server

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

$JAVA -Xms10g -Xmx10g -cp target/many-clients-test-1.0-SNAPSHOT-jar-with-dependencies.jar Server

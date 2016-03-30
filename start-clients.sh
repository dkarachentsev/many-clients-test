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

for ((i=1;i<=$1;i++))
do
    $JAVA -Xms4g -Xmx4g -cp target/apple-test-1.0-SNAPSHOT-jar-with-dependencies.jar Client &
done

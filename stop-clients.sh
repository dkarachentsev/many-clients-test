#!/bin/bash

$JAVA_HOME/bin/jps | grep Client | awk '{print $1}' | xargs kill -9

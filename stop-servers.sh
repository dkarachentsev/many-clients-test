#!/bin/bash

$JAVA_HOME/bin/jps | grep Server | awk '{print $1}' | xargs kill -9

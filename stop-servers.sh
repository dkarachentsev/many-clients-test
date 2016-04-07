#!/bin/bash

jps | grep Server | awk '{print $1}' | xargs kill -9

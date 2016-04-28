#!/bin/bash

jps | grep Client | awk '{print $1}' | xargs kill -9

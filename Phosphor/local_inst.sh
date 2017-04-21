#!/bin/bash

IP=147.8.84.190

FILE_NAME="java-1.8.0-modified.tar.gz"

JDK_FILE=$IP/$FILE_NAME

wget $JDK_FILE

tar -zxf $FILE_NAME

JAVA_HOME=java-8-openjdk-amd64 ./instrumentJRE.sh

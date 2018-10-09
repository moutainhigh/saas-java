#!/bin/sh
java -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=/home/project/hqDocker/logs/memoryError.log -Xms1g -Xmx1g -jar /home/project/hqDocker/@project.build.finalName@.@project.packaging@
#!/bin/bash
make
export _JAVA_OPTIONS="-Xms1024m -Xmx1024m"
java -Djava.library.path=libsysinfo template 


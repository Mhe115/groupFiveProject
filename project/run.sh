#!/bin/bash
make
export _JAVA_OPTIONS="-Xms1024m -Xmx1024m"
sudo apt install maven
java -Djava.library.path=libsysinfo -cp "jFreeChartLib/jfreechart-1.5.3.jar:jFreeChartLib/jcommon-1.0.23.jar:." template


git pull
mvn install
pid=`ps  -ef |grep sm-0.0.1 |grep -v grep | grep -v PPID | awk  '{print $2}'`
kill -9 $pid 
cd target
nohup java -jar sm-0.0.1-SNAPSHOT.jar &
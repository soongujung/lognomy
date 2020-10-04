#!/bin/zsh

name_lognomy_mysql='lognomy-mariadb'

cnt_lognomy_mysql=`docker container ls --filter name=lognomy-mariadb | wc -l`
cnt_lognomy_mysql=$(($cnt_lognomy_mysql -1))

if [ $cnt_lognomy_mysql -eq 0 ]
then
    echo "'$name_lognomy_mysql' 컨테이너가 없습니다. 컨테이너를 구동해주세요."

else
    echo "'$name_lognomy_mysql' 컨테이너의 BASH 쉘 접속을 시작합니다."
    docker container exec -it lognomy-mariadb sh
fi

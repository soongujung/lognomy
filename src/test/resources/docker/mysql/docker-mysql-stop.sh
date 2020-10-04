#!/bin/zsh

name_lognomy_mysql='chartnomy-mariadb'

cnt_lognomy_mysql=`docker container ls --filter name=chartnomy-mariadb | wc -l`
cnt_lognomy_mysql=$(($cnt_lognomy_mysql -1))

if [ $cnt_lognomy_mysql -eq 0 ]
then
    echo "'$name_lognomy_mysql' 컨테이너가 없습니다. 삭제를 진행하지 않습니다."

else
    echo "'$name_lognomy_mysql' 컨테이너가 존재합니다. 기존 컨테이너를 중지하고 삭제합니다."
    docker container stop chartnomy-mariadb
    rm -rf ~/env/docker/chartnomy/volumes/chartnomy-mariadb/*
    echo "\n'$name_lognomy_mysql' 컨테이너 삭제를 완료했습니다.\n"
fi
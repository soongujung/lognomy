#!/bin/zsh

name_lognomy_mysql='lognomy-mariadb'
cnt_lognomy_mysql=`docker container ls --filter name=lognomy-mariadb | wc -l`
cnt_lognomy_mysql=$(($cnt_lognomy_mysql -1))

    if [ $cnt_lognomy_mysql -eq 0 ]
    then
        echo "'$name_lognomy_mysql' 컨테이너를 구동시킵니다.\n"

            # 디렉터리 존재 여부 체크 후 없으면 새로 생성
            DIRECTORY=~$USER/env/docker/lognomy/volumes/lognomy-mariadb
                test -f $DIRECTORY && echo "볼륨 디렉터리가 존재하지 않으므로 새로 생성합니다.\n"

                    if [ $? -lt 1 ]; then
                          mkdir -p ~$USER/env/docker/lognomy/volumes/lognomy-mariadb
                              fi

                                  # mariadb 컨테이너 구동 & 볼륨 마운트
                                  docker container run --rm -d -p 23307:3306 --name lognomy-mariadb \
                                                      -v ~/env/docker/lognomy/volumes/lognomy-mariadb:/var/lib/mysql \
                                                                      -e MYSQL_ROOT_PASSWORD=1111 \
                                                                                      -e MYSQL_DATABASE=lognomy \
                                                                                                      -e MYSQL_USER=testuser \
                                                                                                                      -e MYSQL_PASSWORD=1111 \
                                                                                                                                      -d mariadb:latest \
                                                                                                                                                      --character-set-server=utf8mb4 --collation-server=utf8mb4_unicode_ci

                                                                                                                                                      else
                                                                                                                                                          echo "'$name_lognomy_mysql' 컨테이너가 존재합니다. 기존 컨테이너를 중지하고 삭제합니다."
                                                                                                                                                              # 컨테이너 중지 & 삭제
                                                                                                                                                              docker container stop lognomy-mariadb

                                                                                                                                                                  # 컨테이너 볼륨 삭제
                                                                                                                                                                  rm -rf ~/env/docker/lognomy/volumes/lognomy-mariadb/*
                                                                                                                                                                                                                            echo "\n'$name_lognomy_mysql' 컨테이너 삭제를 완료했습니다.\n"

                                                                                                                                                                                                                                # 디렉터리 존재 여부 체크 후 없으면 새로 생성
                                                                                                                                                                                                                                    DIRECTORY=~$USER/env/docker/lognomy/volumes/lognomy-mariadb
                                                                                                                                                                                                                                        test -f $DIRECTORY && echo "볼륨 디렉터리가 존재하지 않으므로 새로 생성합니다.\n"

                                                                                                                                                                                                                                            if [ $? -lt 1 ]; then
                                                                                                                                                                                                                                                  mkdir -p ~$USER/env/docker/lognomy/volumes/lognomy-mariadb
                                                                                                                                                                                                                                                      fi

                                                                                                                                                                                                                                                          # mariadb 컨테이너 구동 & 볼륨 마운트
                                                                                                                                                                                                                                                              echo "'$name_lognomy_mysql' 컨테이너를 구동시킵니다."
                                                                                                                                                                                                                                                                  docker container run --rm -d -p 23307:3306 --name lognomy-mariadb \
                                                                                                                                                                                                                                                                                  -v ~/env/docker/lognomy/volumes/lognomy-mariadb:/var/lib/mysql \
                                                                                                                                                                                                                                                                                                  -e MYSQL_ROOT_PASSWORD=1111 \
                                                                                                                                                                                                                                                                                                                  -e MYSQL_DATABASE=lognomy \
                                                                                                                                                                                                                                                                                                                                  -e MYSQL_USER=testuser \
                                                                                                                                                                                                                                                                                                                                                  -e MYSQL_PASSWORD=1111 \
                                                                                                                                                                                                                                                                                                                                                                  -d mariadb:latest \
                                                                                                                                                                                                                                                                                                                                                                                  --character-set-server=utf8mb4 --collation-server=utf8mb4_unicode_ci
                                                                                                                                                                                                                                                                                                                                                                                  fi


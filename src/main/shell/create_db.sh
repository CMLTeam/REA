#!/usr/bin/env bash

echo "
create database rea CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
grant all privileges on rea.* to 'rea'@'%' identified by 'rea';
flush privileges;
" | mysql -uroot -p

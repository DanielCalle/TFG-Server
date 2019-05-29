#!/bin/sh
echo start postgresql
service postgresql start

echo user postgresql
sudo -i -u postgres

echo open psql
psql
\password

echo enter pass
admin

echo create database 
create database films;

echo exit
\q

echo import
psql -d films -f /usr/src/app/src/main/webapp/WEB-INF/sql/filmar_low.sql

exit
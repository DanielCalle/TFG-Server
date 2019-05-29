#!/bin/sh
service postgresql start
sudo -i -u postgres
psql
\password
admin
create database films;
\q
psql -d films -f /usr/src/app/src/main/webapp/WEB-INF/sql/filmar_low.sql
exit
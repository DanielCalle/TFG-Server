#!/bin/sh
echo Starting postgresql ...
service postgresql start

sudo -u postgres createdb film
sudo -u postgres psql film < /usr/src/app/src/main/webapp/WEB-INF/sql/filmar_low.sql
echo Finished.

exit
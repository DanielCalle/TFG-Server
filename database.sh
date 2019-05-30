#!/bin/sh
echo Starting postgresql ...

service postgresql start

sudo -u postgres createdb films

sudo -u postgres psql -U postgres -d films -c "alter user postgres with password 'admin';"

sudo -u postgres psql films < /usr/src/app/src/main/webapp/WEB-INF/sql/filmar_low.sql

echo Finished.

exit

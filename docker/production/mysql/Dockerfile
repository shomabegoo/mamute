FROM mysql:latest

# more info: http://stackoverflow.com/a/37248560/225052
RUN echo "[mysqld]\nsql_mode = \"STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION\"" >> /etc/mysql/my.cnf
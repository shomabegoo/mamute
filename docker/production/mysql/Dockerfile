FROM mysql:5.7.22

# more info: http://stackoverflow.com/a/37248560/225052
RUN echo "[mysqld]\ndefault_password_lifetime=0\nsql_mode = \"STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION\"" >> /etc/mysql/my.cnf

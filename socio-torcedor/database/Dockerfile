FROM postgres

COPY pg_hba.conf /usr/share/postgresql/9.6/
COPY postgresql.conf /usr/share/postgresql/9.6/
ADD  docker-entrypoint-initdb.d/ /docker-entrypoint-initdb.d/

# Default values for passwords and database name. Can be overridden on docker run
ENV POSTGRES_USER sociotorcedor
ENV POSTGRES_DB socio

FROM java:8-jdk

RUN apt-get update && apt-get install -y gettext-base

# Mamute VERSION --> Tested for v1.3 and 1.4
# On change rember to edit the variables also in ../docker-compose.yml
ENV VERSION 1.4.0
ARG USER
ARG PASSWORD
ARG DB
ARG GOOGLE_CLIENT_SECRET
ARG FACEBOOK_APP_SECRET
ARG VRAPTOR_SIMPLEMAIL_SEND_REAL_EMAIL
ARG VRAPTOR_SIMPLEMAIL_MAIN_PASSWORD
ARG MAIN_SITE_URL


RUN mkdir /mamute
WORKDIR /mamute

ADD mamute-1.4.0.war ./
RUN unzip mamute-${VERSION}.war && \
    rm mamute-${VERSION}.war

ADD ./production.properties /mamute/WEB-INF/classes/production.template
RUN envsubst < /mamute/WEB-INF/classes/production.template > /mamute/WEB-INF/classes/production.properties

RUN if [ "$VERSION" = "1.3.0" ]; then mv /mamute/WEB-INF/classes/production/hibernate.cfg.xml.example /mamute/WEB-INF/classes/production/hibernate.cfg.xml; fi

RUN sed -i -e "s?>root<?>$USER<?g" /mamute/WEB-INF/classes/production/hibernate.cfg.xml && \
    sed -i -e "s?><?>$PASSWORD<?g" WEB-INF/classes/production/hibernate.cfg.xml && \
    sed -i -e "s?localhost/mamute_production?db/$DB?g" WEB-INF/classes/production/hibernate.cfg.xml

RUN chmod +x run.sh

EXPOSE 8080

# Wait for intial Database init --> TODO: Find a better way to wait for the DB
CMD sleep 15 && VRAPTOR_ENV=production ./run.sh

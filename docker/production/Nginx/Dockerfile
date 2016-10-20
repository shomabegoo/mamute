FROM nginx:1.9

ARG MAIN_SITE_URL
ARG TABAYE_URL

ADD nginx.conf /etc/nginx/nginx.conf
ADD conf.d/shomabegoo.conf /tmp/shomabegoo.template
ADD conf.d/tabaye.conf /tmp/tabaye.template

RUN envsubst '${MAIN_SITE_URL}' < /tmp/shomabegoo.template > /etc/nginx/conf.d/shomabegoo.conf
RUN envsubst '${TABAYE_URL}' < /tmp/tabaye.template > /etc/nginx/conf.d/tabaye.conf
FROM nginx:latest
WORKDIR /vue_cms
ENV LANG en US.UTF-8
RUN mkdir  /usr/share/nginx/html/cms_01
COPY ./front/dist/ /usr/share/nginx/html/cms_01/
COPY ./front/nginx/cms_01.conf /etc/nginx/conf.d/default.conf
EXPOSE 80
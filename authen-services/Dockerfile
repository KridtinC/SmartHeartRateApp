FROM node:10-alpine

LABEL maintainer "KridtinC <kridtin.ch@gmail.com>"

RUN mkdir -p /authen-services
WORKDIR /authen-services

COPY package.json /authen-services
RUN npm install

COPY . /authen-services

EXPOSE 5001

CMD ["node", "/authen-services/app.js"]
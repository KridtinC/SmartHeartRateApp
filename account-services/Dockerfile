FROM node:10-alpine

LABEL maintainer "KridtinC <kridtin.ch@gmail.com>"

RUN mkdir -p /account-services
WORKDIR /account-services

COPY package.json /account-services
RUN npm install

COPY . /account-services

EXPOSE 5000

CMD ["node", "/account-services/app.js"]
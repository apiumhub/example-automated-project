FROM node:4.1.1-wheezy

RUN mkdir /app
WORKDIR /app/

COPY . ./

RUN npm install
CMD node src/main.js

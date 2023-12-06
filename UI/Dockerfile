FROM node:21.4.0-bullseye-slim AS builder

RUN mkdir /project
WORKDIR /project

RUN npm install -g npm@9.6.0
RUN npm install -g @angular/cli@13

COPY package.json package-lock.json ./
RUN npm ci

COPY . .

CMD ["ng", "serve", "--host", "0.0.0.0", "--configuration=production","--disable-host-check"]

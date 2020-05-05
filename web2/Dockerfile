### STAGE 1: Build ###
FROM node:12.7-alpine AS build
WORKDIR /app
COPY package.json ./
RUN npm install
COPY . .
RUN npm run build

### STAGE 2: Run ###
FROM nginx:1.17.1-alpine
COPY default.conf /etc/nginx/conf.d/default.conf
COPY --from=build /app/dist/shoppingcart-ui /usr/share/nginx/html
EXPOSE 80



# # base image
# FROM node:12.2.0

# # set working directory
# WORKDIR /app

# # add `/app/node_modules/.bin` to $PATH
# ENV PATH /app/node_modules/.bin:$PATH

# # install and cache app dependencies
# COPY package.json /app/package.json
# RUN npm install
# RUN npm install -g @angular/cli@7.3.9

# # add app
# COPY . /app

# # start app
# CMD ng serve --host 0.0.0.0

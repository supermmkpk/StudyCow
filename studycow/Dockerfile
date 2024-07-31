# 빌드 단계
FROM node:20 as build
WORKDIR /app
COPY package*.json ./usr/src/app
RUN npm install
COPY . .
RUN npm run build

# 프로덕션 단계
FROM nginx:alpine
COPY --from=build /app/build /usr/share/nginx/html
COPY nginx.conf /etc/nginx/conf.d/default.conf
EXPOSE 88
CMD ["nginx", "-g", "daemon off;"]

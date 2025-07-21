pipeline {
  agent any
  environment {
    REGISTRY = 'Docker-credentials'  // Docker Hub credentials ID in Jenkins
  }

  stages {
    stage('Checkout') {
      steps {
        git branch: 'main', url: 'https://github.com/Gaurav560/student-management-system-frontend.git'
      }
    }

    stage('Create Dockerfile and K8s Manifest') {
      steps {
        script {
          // ✅ Fixed Dockerfile (changed /app/build to /app/dist)
          writeFile file: 'Dockerfile', text: '''
FROM node:18-alpine AS build
WORKDIR /app
COPY package*.json ./
RUN npm install
COPY . .
RUN npm run build

FROM nginx:alpine
COPY --from=build /app/dist /usr/share/nginx/html
EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]
'''
        }
      }   
      }
      
    stage('Docker Build & Push') {
      steps {
        script {
          def imageTag = "saidocker567/react-app:${env.BUILD_NUMBER}"
          docker.build(imageTag, '.')
          docker.withRegistry('', REGISTRY) {
            docker.image(imageTag).push()
            docker.image(imageTag).push('latest')
          }
        }
      }
    }
    }
  }
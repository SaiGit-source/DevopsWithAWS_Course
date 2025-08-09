pipeline {
  agent any
  environment {
    REGISTRY = 'Sai_Docker_ID1'  // Docker Hub credentials ID in Jenkins
    KUBECONFIG = '/var/lib/jenkins/.kube/config'  // Path to kubeconfig on Jenkins server
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

          // Kubernetes Deployment YAML
          writeFile file: 'react-deployment.yaml', text: """
apiVersion: apps/v1
kind: Deployment
metadata:
  name: react-deploy
spec:
  replicas: 2
  selector:
    matchLabels:
      app: react-app
  template:
    metadata:
      labels:
        app: react-app
    spec:
      containers:
      - name: react-container
        image: saidocker567/react-app:${env.BUILD_NUMBER}
        ports:
        - containerPort: 80
---
apiVersion: v1
kind: Service
metadata:
  name: react-service
spec:
  selector:
    app: react-app
  ports:
  - protocol: TCP
    port: 80
    targetPort: 80
  type: LoadBalancer
"""
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

    stage('Deploy to Kubernetes') {
      steps {
        sh """
          export KUBECONFIG=${env.KUBECONFIG}
          kubectl apply -f react-deployment.yaml
          kubectl set image deployment/react-deploy react-container=saidocker567/react-app:${env.BUILD_NUMBER}
          kubectl rollout status deployment/react-deploy
        """
        }
      }
    }
  }

pipeline {
  agent any

  environment {
    REGISTRY = 'Sai_Docker_ID1'  // Docker Hub credentials ID in Jenkins
    KUBECONFIG = '/var/lib/jenkins/.kube/config'  // Path to kubeconfig on Jenkins server
  }

  stages {

    stage('Checkout') {
      steps {
        git branch: 'main', url: 'https://github.com/Gaurav560/SMProject.git'
      }
    }

    stage('Create Dockerfile and K8s Manifest') {
      steps {
        script {
          // ✅ Dockerfile for Spring Boot inside subdirectory
          writeFile file: 'Dockerfile', text: '''
FROM openjdk:17-alpine
VOLUME /tmp
COPY BackEndSMProject/target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
          '''

          // ✅ Kubernetes Deployment and Service YAML
          writeFile file: 'spring-deployment.yaml', text: """
apiVersion: apps/v1
kind: Deployment
metadata:
  name: springboot-deploy
spec:
  replicas: 2
  selector:
    matchLabels:
      app: springboot-app
  template:
    metadata:
      labels:
        app: springboot-app
    spec:
      containers:
      - name: springboot-container
        image: saidocker567/springboot-app:${env.BUILD_NUMBER}
        ports:
        - containerPort: 8080
        env:
        - name: SPRING_PROFILES_ACTIVE
          value: "prod"
---
apiVersion: v1
kind: Service
metadata:
  name: springboot-service
spec:
  selector:
    app: springboot-app
  ports:
  - protocol: TCP
    port: 8080
    targetPort: 8080
  type: LoadBalancer
"""
        }
      }
    }

    stage('Build Spring Boot App') {
      steps {
        // Fix permissions then build inside subdirectory
        sh '''
          cd BackEndSMProject
          chmod +x mvnw
          ./mvnw clean package -DskipTests
        '''
      }
    }

    stage('Docker Build & Push') {
      steps {
        script {
          def imageTag = "saidocker567/springboot-app:${env.BUILD_NUMBER}"
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
          kubectl get nodes
          kubectl apply -f spring-deployment.yaml  --validate=false
          kubectl set image deployment/springboot-deploy springboot-container=saidocker567/springboot-app:${env.BUILD_NUMBER}
          kubectl rollout status deployment/springboot-deploy
        """
      }
    }
  }
}
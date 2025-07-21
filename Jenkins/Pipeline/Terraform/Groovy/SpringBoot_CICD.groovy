pipeline {
  agent any

  environment {
    REGISTRY = 'Docker-credentials'  // Docker Hub credentials ID in Jenkins
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
  }
}
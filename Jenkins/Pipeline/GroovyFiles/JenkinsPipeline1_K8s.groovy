pipeline {
agent any

environment {
	IMAGE_NAME = "my-web-app"
	DOCKER_TAG = "latest"
}
    
    tools {
        maven "maven-3.9.10"
    }

    stages {
        stage('git clone') {
            steps {
                git branch: 'main', url: 'https://github.com/SaiGit-source/SpringWebApp.git'
            }
        }
        stage('maven build') {
            steps {
                sh 'mvn clean package'
            }
        }
        stage('Build Docker Image') {
            steps {
                script {
                    writeFile file: 'Dockerfile', text: '''
                    # Use an official Tomcat base image
                    FROM tomcat:latest
                    LABEL maintainer="DemoDockerfile"

                    # Remove default webapps
                    RUN rm -rf /usr/local/tomcat/webapps/*

                    # Copy WAR to Tomcat webapps
                    COPY target/*.war /usr/local/tomcat/webapps/ROOT.war

                    # Expose port 
                    EXPOSE 8080
                '''
                    echo "✅ Dockerfile generated"

	            sh "docker build -t ${IMAGE_NAME}:${DOCKER_TAG} ."
                }            
            }
        }
        
        stage('Docker push') {
            steps {
                withCredentials([string(credentialsId: 'Sai-Docker-Pwd', variable: 'Docker_Hub_PWD_New')]) 	{
                        sh 'docker login -u saidocker567 -p ${Docker_Hub_PWD_New}'
                        sh 'docker tag ${IMAGE_NAME}:${DOCKER_TAG} saidocker567/${IMAGE_NAME}:${DOCKER_TAG}'
                        sh 'docker push saidocker567/${IMAGE_NAME}:${DOCKER_TAG}'
                }
            }
        }

        stage('Deploy to Kubernetes') {
            steps {
                script {
                    // Write Kubernetes Deployment YAML
                    writeFile file: 'deployment.yaml', text: '''
apiVersion: apps/v1
kind: Deployment
metadata:
  name: web-app-deployment
spec:
  replicas: 2
  selector:
    matchLabels:
      app: web-app
  template:
    metadata:
      labels:
        app: web-app
    spec:
      containers:
      - name: web-container
        image: saidocker567/my-web-app:latest
        ports:
        - containerPort: 8080
                    '''

                    // Write Kubernetes Service YAML
                    writeFile file: 'service.yaml', text: '''
apiVersion: v1
kind: Service
metadata:
  name: web-app-service
spec:
  type: LoadBalancer
  selector:
    app: web-app
  ports:
    - protocol: TCP
      port: 80
      targetPort: 8080
                    '''

                    // Apply Kubernetes manifests
                    sh 'kubectl apply -f deployment.yaml'
                    sh 'kubectl apply -f service.yaml'
                }
            }
        }
}
}
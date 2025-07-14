pipeline {
agent any

environment {
	IMAGE_NAME = "ci-web-app"
	DOCKER_TAG = "latest"
}
    
    tools {
        maven "maven-3.9.10"
    }

    stages {
        stage('git clone') {
            steps {
                git branch: 'main', url: 'https://github.com/Haider7214/WebAppMaven.git'
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

        stage('Trigger CD job if CI is successful') {
            steps {
                build 'CD_WebApp_Pipeline'
            }
        }
}
}
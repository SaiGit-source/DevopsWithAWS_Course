pipeline {
    agent any
    tools {
		maven "maven-3.9.10"
	}

    stages {
        stage('git clone') {
            steps {
                git branch: 'main', url: 'https://github.com/Haider7214/WebAppMaven.git'
            }
        }
	stage('k8s - deployment') {
            steps {
                sh 'kubectl apply -f k8s-deployment.yaml'
                
            }
        }
    }
}
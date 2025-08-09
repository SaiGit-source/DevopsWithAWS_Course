pipeline {
    agent any
    
    tools {
        maven "maven-3.9.10"
    }

    stages {
        stage('git clone') {
            steps {
                git branch: 'main', url: 'https://github.com/Haider7214/SpringApp.git'
            }
        }
        stage('maven build') {
            steps {
                sh 'mvn clean compile test package'
            }
        }
        stage('parallel stage') {
            parallel {
                stage('code-review'){
                    steps {
                        echo 'code review'
                        }
                }
                
                stage('nexus-upload'){
                    steps {
                        echo 'nexus upload'
                        }
                }
            }

        }
        stage('app deployed') {
            steps {
                echo 'Deploying App with Tomcat'
            }
        }
    }
}
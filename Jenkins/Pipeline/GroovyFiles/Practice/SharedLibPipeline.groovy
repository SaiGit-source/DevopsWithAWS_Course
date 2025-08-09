@Library('demo_shared_lib')_
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
        stage('demo message') {
            steps {
                telusko()
            }
        }
        stage('maven build') {
            steps {
                mavenBuild()
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
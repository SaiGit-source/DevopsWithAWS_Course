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
        stage('App deployment') {
            steps {
                sshagent(['Tomcat-Server-Credentials-Pipeline1']) {
                    sh 'scp -o StrictHostKeyChecking=no target/FirstSpringWebApp-0.0.1-SNAPSHOT.war ec2-user@15.156.94.232:/home/ec2-user/apache-tomcat-11.0.8/webapps'
                }
            }
        }
    }
}
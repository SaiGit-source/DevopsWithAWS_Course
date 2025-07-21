pipeline {
    agent any
    environment {
        AWS_ACCESS_KEY_ID = credentials('AWS_ACCESS_KEY_ID')
        AWS_SECRET_ACCESS_KEY = credentials('AWS_SECRET_ACCESS_KEY')
        AWS_DEFAULT_REGION = 'ca-central-1'
    }
    stages{
        stage('Checkout SCM'){
            steps{
                script{
                    git branch: 'main', url: 'https://github.com/SaiGit-source/Terraform2.git'
                }
            }
        }
        stage('Initializing Terraform'){
            steps{
                script{
                    dir('terraform'){
                         sh 'terraform init -upgrade'
                    }
                }
            }
        }
	stage('Validating Terraform'){
            steps{
                script{
                    dir('terraform'){
                         sh 'terraform validate'
                    }
                }
            }
        }

        stage('Previewing the infrastructure to be created'){
            steps{
                script{
                    dir('terraform'){
                         sh 'terraform plan'
                    }
                }
            }
        }
        stage('Create/Destroy an EKS cluster'){
            steps{
                script{
                    dir('terraform'){
                         sh 'terraform apply --auto-approve'
                    }
                }
            }
        }
    }
}
pipeline {
    agent any
    
    tools {
        gradle 'Gradle'
        nodejs 'Node'
    }
    
    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }
        
        stage('Build Backend') {
            steps {
                dir('backend') {
                    sh 'gradle clean build'
                }
            }
        }
        
        stage('Build Frontend') {
            steps {
                dir('studycow') {
                    sh 'npm install'
                    sh 'npm run build'
                }
            }
        }
        
        stage('Test') {
            parallel {
                stage('Backend Tests') {
                    steps {
                        dir('backend') {
                            sh 'gradle test'
                        }
                    }
                }
                stage('Frontend Tests') {
                    steps {
                        dir('studycow') {
                            sh 'npm test'
                        }
                    }
                }
            }
        }
        
        stage('Deploy') {
            steps {
                sshagent(['ec2-ssh-key-credential-id']) {
                    sh '''
                        scp -o StrictHostKeyChecking=no backend/build/libs/*.jar ec2-user@your-ec2-instance:/path/to/deployment
                        scp -o StrictHostKeyChecking=no -r frontend/build/* ec2-user@your-ec2-instance:/path/to/deployment/frontend
                        ssh -o StrictHostKeyChecking=no ec2-user@your-ec2-instance "sudo systemctl restart your-application"
                    '''
                }
            }
        }
    }
    
    post {
        success {
            echo 'CI/CD pipeline executed successfully!'
        }
        failure {
            echo 'CI/CD pipeline failed. Please check the logs for details.'
        }
    }
}

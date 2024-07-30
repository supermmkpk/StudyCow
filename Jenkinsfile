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
            }
        }
        
        stage('Deploy') {
            steps {
                sshPublisher(
                    publishers: [
                        sshPublisherDesc(
                            configName: 'i11c202.p.ssafy.io',
                            transfers: [
                                sshTransfer(
                                    sourceFiles: 'backend/build/libs/*.jar',
                                    removePrefix: 'backend/build/libs',
                                    remoteDirectory: '/home/ubuntu',
                                    execCommand: '''
                                        sudo mkdir -p /opt/studycow/backend
                                        sudo mv /home/ubuntu/*.jar /opt/studycow/backend/studycow-backend.jar
                                        sudo chown -R ubuntu:ubuntu /opt/studycow/backend
                                        sudo systemctl restart studycow-backend || sudo systemctl start studycow-backend
                                    '''
                                ),
                                sshTransfer(
                                    sourceFiles: 'studycow/dist/**/*',
                                    removePrefix: 'studycow/dist',
                                    remoteDirectory: '/home/ubuntu/frontend-temp',
                                    execCommand: '''
                                        sudo mkdir -p /var/www/studycow
                                        sudo rm -rf /var/www/studycow/*
                                        sudo mv /home/ubuntu/frontend-temp/* /var/www/studycow/
                                        sudo chown -R ubuntu:ubuntu /var/www/studycow
                                        sudo systemctl restart nginx || sudo systemctl start nginx
                                    '''
                                )
                            ],
                            usePromotionTimestamp: false,
                            useWorkspaceInPromotion: false,
                            verbose: true
                        )
                    ]
                )
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

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
                // 프론트엔드 테스트가 필요하다면 여기에 추가할 수 있습니다.
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
                                    remoteDirectory: '/opt/studycow/backend',
                                    execCommand: '''
                                        sudo mkdir -p /opt/studycow/backend
                                        sudo cp /opt/studycow/backend/*.jar /opt/studycow/backend/studycow-backend.jar
                                        sudo chown -R ubuntu:ubuntu /opt/studycow/backend
                                        sudo systemctl restart studycow-backend
                                    '''
                                ),
                                sshTransfer(
                                    sourceFiles: 'studycow/dist/**/*',
                                    removePrefix: 'studycow/dist',
                                    remoteDirectory: '/var/www/studycow',
                                    execCommand: '''
                                        sudo mkdir -p /var/www/studycow
                                        sudo chown -R ubuntu:ubuntu /var/www/studycow
                                        sudo systemctl restart nginx
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

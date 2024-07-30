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
                                    remoteDirectory: '/path/to/deployment',
                                    execCommand: 'sudo systemctl restart your-application'
                                ),
                                sshTransfer(
                                    sourceFiles: 'studycow/build/**/*',
                                    removePrefix: 'studycow/build',
                                    remoteDirectory: '/path/to/deployment/frontend',
                                    execCommand: ''
                                )
                            ],
                            usePromotionTimestamp: false,
                            useWorkspaceInPromotion: false,
                            verbose: false
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

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
                dir('studycow') { // 디렉토리 이름을 'studycow'로 수정
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
                // 추가적인 테스트 스테이지가 필요한 경우 여기에 추가할 수 있습니다.
            }
        }
        
        stage('Deploy') {
            steps {
                sshagent(['ec2-ssh-key-credential-id']) {
                    sh '''
                         ssh -o StrictHostKeyChecking=no ec2-user@your-ec2-instance "ls -al"
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

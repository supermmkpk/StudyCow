pipeline {
    agent any
    
    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }
        
        stage('Backend - Test') {
            steps {
                dir('backend') {
                    sh './gradlew test'
                }
            }
        }
        
        stage('Backend - Build') {
            steps {
                dir('backend') {
                    sh './gradlew build -x test'
                }
            }
        }
        
        stage('Frontend - Build') {
            steps {
                dir('studycow') {
                    sh 'npm install'
                    sh 'npm run build'
                }
            }
        }
        
        stage('Docker Build and Deploy') {
            steps {
                sh 'docker-compose down || true'
                sh 'docker-compose build'
                sh 'docker-compose up -d'
            }
        }
        
        stage('Health Check') {
            steps {
                script {
                    sh 'sleep 30'  // 애플리케이션 시작 대기
                    sh 'curl http://localhost:8080/api/health || exit 1'  // 백엔드 헬스 체크
                    sh 'curl http://localhost || exit 1'  // 프론트엔드 헬스 체크
                }
            }
        }
    }
    
    post {
        always {
            junit '**/backend/build/test-results/test/*.xml'
        }
        failure {
            sh 'docker-compose logs'
        }
    }
}

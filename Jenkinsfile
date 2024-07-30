pipeline {
    agent any
    
    environment {
        DOCKER_NETWORK = "studycow_network"
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }
        
        stage('Backend - Build') {
            steps {
                dir('backend') {
                    sh 'chmod +x ./gradlew'
                    sh './gradlew build -x test'
                    sh 'docker build -t backend:${BUILD_NUMBER} .'
                }
            }
        }
        
        stage('Frontend - Build') {
            steps {
                dir('studycow') {
                    sh 'npm install'
                    sh 'npm run build'
                    sh 'docker build -t frontend:${BUILD_NUMBER} .'
                }
            }
        }
        
        stage('Deploy') {
            steps {
                script {
                    // 기존 컨테이너 정지 및 제거
                    sh 'docker stop backend frontend || true'
                    sh 'docker rm backend frontend || true'
                    
                    // 네트워크 생성 (이미 존재하지 않는 경우)
                    sh "docker network create ${DOCKER_NETWORK} || true"
                    
                    // 백엔드 실행
                    sh "docker run -d --name backend --network ${DOCKER_NETWORK} -p 8080:8080 backend:${BUILD_NUMBER}"
                    
                    // 프론트엔드 실행
                    sh "docker run -d --name frontend --network ${DOCKER_NETWORK} -p 80:80 frontend:${BUILD_NUMBER}"
                }
            }
        }
        
        stage('Health Check') {
            steps {
                script {
                    sh 'sleep 30'
                    sh 'curl http://localhost:8080/api/health || true'
                    sh 'curl http://localhost || true'
                }
            }
        }
    }
    
    post {
        always {
            sh 'docker logs backend || true'
            sh 'docker logs frontend || true'
        }
        failure {
            sh 'docker stop backend frontend || true'
            sh 'docker rm backend frontend || true'
        }
    }
}

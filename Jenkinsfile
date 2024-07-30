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
                    sh './gradlew clean bootJar'
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
                    sh "docker network create ${DOCKER_NETWORK} || true"
                    
                    sh 'docker stop backend || true'
                    sh 'docker rm backend || true'
                    sh "docker run -d --name backend --network ${DOCKER_NETWORK} -p 8085:8085 backend:${BUILD_NUMBER}"
                    
                    sh 'docker stop frontend || true'
                    sh 'docker rm frontend || true'
                    sh "docker run -d --name frontend --network ${DOCKER_NETWORK} -p 80:80 frontend:${BUILD_NUMBER}"
                }
            }
        }
        
        stage('Health Check') {
            steps {
                script {
                    sh 'sleep 30'
                    sh 'curl http://localhost:8085/studycow || echo "Backend health check failed"'
                    sh 'curl http://localhost || echo "Frontend health check failed"'
                }
            }
        }
    }
    
    post {
        always {
            sh 'docker logs backend || echo "No backend logs available"'
            sh 'docker logs frontend || echo "No frontend logs available"'
        }
        failure {
            sh 'docker stop backend frontend || true'
            sh 'docker rm backend frontend || true'
        }
        cleanup {
            cleanWs()
        }
    }
}

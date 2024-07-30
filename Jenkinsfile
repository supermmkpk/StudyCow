pipeline {
    agent any
    
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
                    sh 'docker-compose down || true'
                    sh 'docker-compose up -d'
                }
            }
        }
        
        stage('Health Check') {
            steps {
                script {
                    sh 'sleep 30'
                    sh 'curl http://localhost:8085 || echo "Backend health check failed"'
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
            sh 'docker-compose down || true'
        }
        cleanup {
            cleanWs()
        }
    }
}

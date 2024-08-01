pipeline {
    agent any
    
    environment {
        DOCKER_NETWORK = "studycow_network"
        GPT_API_KEY = credentials('gpt-api-key-id')
        VITE_API_BASE_URL = 'https://i11c202.p.ssafy.io/studycow/'
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }
        
        stage('Backend - Build and Test') {
            steps {
                dir('backend') {
                    sh 'chmod +x ./gradlew'
                    sh './gradlew clean bootJar'
                    sh './gradlew test' // 백엔드 테스트 추가
                    sh 'docker build -t backend:${BUILD_NUMBER} .'
                }
            }
        }
        
        stage('Frontend - Build and Test') {
            steps {
                dir('studycow') {
                    sh 'npm install'
                    sh 'npm run build'
                    sh 'ls -la build'
                    sh 'cat build/index.html || echo "index.html not found"'
                    sh 'echo "VITE_API_BASE_URL=${VITE_API_BASE_URL}" > .env'  
                    sh 'docker build -t frontend:${BUILD_NUMBER} --build-arg VITE_API_BASE_URL=${VITE_API_BASE_URL} .'
                }
            }
        }
        
            stages {
                stage('Backend - Deploy') {
                    steps {
                        script {
                            sh "docker network create ${DOCKER_NETWORK} || true"
                            
                            sh 'docker stop backend || true'
                            sh 'docker rm backend || true'
                            sh "docker run -d --name backend --network ${DOCKER_NETWORK} -p 8080:8080 -e GPT_API_KEY=${GPT_API_KEY} backend:${BUILD_NUMBER}"
                        }
                    }
                }
                
                stage('Frontend - Deploy') {
                    steps {
                        script {
                            sh 'docker stop frontend || true'
                            sh 'docker rm frontend || true'
                            sh 'docker run -d --name frontend --network studycow_network -p 3000:80 frontend:${BUILD_NUMBER}'
                            sh 'docker cp frontend:/usr/share/nginx/html ./nginx-content'
                        }
                    }
                }
                
                stage('Health Check') {
                    steps {
                        script {
                            sh 'sleep 3'  // 서비스가 시작될 시간을 좀 더 주었습니다.
                            sh 'curl -f http://13.125.238.202:8080 || (echo "Backend health check failed" && exit 1)'
                            sh 'curl -f http://localhost:8080 || (echo "Backend local test failed" && exit 1)'
                            sh 'curl -f http://13.125.238.202 || (echo "Frontend health check failed" && exit 1)'
                        }
                    }
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

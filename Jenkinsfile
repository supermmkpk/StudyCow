pipeline {
    agent any
    
    environment {
        DOCKER_NETWORK = 'backend'
        DB_ROOT_PASSWORD = '1emdgkrhthajrwk'
        TZ = 'Asia/Seoul'
        KMS_STUN_IP = '13.125.238.202'
        KMS_STUN_PORT = '3478'
        KMS_TURN_URL = 'myuser:mypassword@13.125.238.202:3478?transport=udp'
        SPRING_DATASOURCE_URL = 'jdbc:mysql://db:3306/study_cow?useUnicode=true&characterEncoding=utf8&serverTimezone=Asia/Seoul&zeroDateTimeBehavior=convertToNull&rewriteBatchedStatements=true'
        JAVA_TOOL_OPTIONS = '-Dkms.url=ws://kurento:8888/kurento'
    }
    
    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }
        
        stage('Prepare Environment') {
            steps {
                sh 'docker-compose down || true'
                sh 'docker rm -f $(docker ps -aq) || true'
                sh 'docker network create ${DOCKER_NETWORK} || true'
            }
        }
        
        stage('Deploy Database') {
            steps {
                sh '''
                    docker run -d --name study_cow_db \
                        --network ${DOCKER_NETWORK} \
                        -p 32000:3306 \
                        -e MYSQL_ROOT_PASSWORD=${DB_ROOT_PASSWORD} \
                        -e TZ=${TZ} \
                        -v $WORKSPACE/db:/docker-entrypoint-initdb.d \
                        mysql:5.7
                '''
            }
        }
        
        stage('Deploy Kurento') {
            steps {
                sh '''
                    docker run -d --name kurento \
                        --network ${DOCKER_NETWORK} \
                        -p 8888:8888 \
                        -e TZ=${TZ} \
                        -e KMS_STUN_IP=${KMS_STUN_IP} \
                        -e KMS_STUN_PORT=${KMS_STUN_PORT} \
                        -e KMS_TURN_URL=${KMS_TURN_URL} \
                        kurento/kurento-media-server:latest
                '''
            }
        }
        
        stage('Backend - Build and Deploy') {
            steps {
                dir('backend') {
                    sh 'chmod +x ./gradlew'
                    sh './gradlew clean bootJar'
                    sh 'docker build -t backend:${BUILD_NUMBER} .'
                    sh '''
                        docker run -d --name study_cow_app \
                            --network ${DOCKER_NETWORK} \
                            -p 8080:8080 -p 8443:8443 \
                            -e TZ=${TZ} \
                            -e SPRING_DATASOURCE_URL=${SPRING_DATASOURCE_URL} \
                            -e JAVA_TOOL_OPTIONS=${JAVA_TOOL_OPTIONS} \
                            -e server.address=0.0.0.0 \
                            backend:${BUILD_NUMBER}
                    '''
                }
            }
        }
        
        stage('Frontend - Build and Deploy') {
            steps {
                dir('studycow') {
                    sh 'npm install'
                    sh 'npm run build'
                    sh 'docker build -t frontend:${BUILD_NUMBER} .'
                    sh '''
                        docker run -d --name frontend-${BUILD_NUMBER} \
                            --network ${DOCKER_NETWORK} \
                            -p 80:80 \
                            frontend:${BUILD_NUMBER}
                    '''
                }
            }
        }
        
        stage('Health Check') {
            steps {
                script {
                    sh 'sleep 60'  // 서비스가 완전히 시작될 때까지 더 오래 기다립니다.
                    retry(3) {
                        sh 'curl -f http://localhost:8080/api/health || (sleep 10 && false)'
                        sh 'curl -f http://localhost || (sleep 10 && false)'
                    }
                }
            }
        }
    }
    
    post {
        always {
            sh 'docker logs study_cow_app || echo "No backend logs available"'
            sh 'docker logs frontend-${BUILD_NUMBER} || echo "No frontend logs available"'
        }
        failure {
            sh 'docker-compose down || true'
            sh 'docker stop $(docker ps -aq) || true'
            sh 'docker rm $(docker ps -aq) || true'
            sh 'docker network rm ${DOCKER_NETWORK} || true'
        }
        cleanup {
            cleanWs()
        }
    }
}

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
                sh 'docker network create ${DOCKER_NETWORK} || true'
            }
        }

        stage('Build Backend') {
            steps {
                dir('backend') {
                    sh 'chmod +x ./gradlew'
                    sh './gradlew clean bootJar'
                    sh 'docker build -t backend:${BUILD_NUMBER} .'
                }
            }
        }

        stage('Build Frontend') {
            steps {
                dir('studycow') {
                    sh 'npm install'
                    sh 'npm run build'
                    sh 'docker build -t frontend:${BUILD_NUMBER} .'
                }
            }
        }

        stage('Deploy Services') {
            steps {
                sh 'docker-compose up -d'
            }
        }

        stage('Health Check') {
            steps {
                script {
                    sh 'sleep 60'  // 서비스가 완전히 시작될 때까지 기다립니다.
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
            sh 'docker logs frontend || echo "No frontend logs available"'
        }
        failure {
            sh 'docker-compose down || true'
            sh 'docker network rm ${DOCKER_NETWORK} || true'
        }
        cleanup {
            cleanWs()
        }
    }
}

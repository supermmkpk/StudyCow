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

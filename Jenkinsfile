sshPublisher(
    publishers: [
        sshPublisherDesc(
            configName: 'i11c202.p.ssafy.io',
            transfers: [
                sshTransfer(
                    sourceFiles: 'backend/build/libs/*.jar',
                    remoteDirectory: '.',
                    execCommand: '''
                        sudo mkdir -p /opt/studycow/backend
                        sudo mv ~/*.jar /opt/studycow/backend/studycow-backend.jar
                        sudo chown -R ubuntu:ubuntu /opt/studycow/backend
                        sudo systemctl restart studycow-backend || sudo systemctl start studycow-backend
                    '''
                ),
                sshTransfer(
                    sourceFiles: 'studycow/dist/**/*',
                    removePrefix: 'studycow/dist',
                    remoteDirectory: './frontend-temp',
                    execCommand: '''
                        sudo mkdir -p /var/www/studycow
                        sudo rm -rf /var/www/studycow/*
                        sudo mv ~/frontend-temp/* /var/www/studycow/
                        sudo rm -rf ~/frontend-temp
                        sudo chown -R ubuntu:ubuntu /var/www/studycow
                        sudo systemctl restart nginx || sudo systemctl start nginx
                    '''
                )
            ],
            usePromotionTimestamp: false,
            useWorkspaceInPromotion: false,
            verbose: true
        )
    ]
)

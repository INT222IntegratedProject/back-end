pipeline {
    agent any
    stages {

        stage('stop and remove container, image') {
            steps {
                script {
                    def imageExists = sh(script: 'docker images -q backend', returnStdout: true) == ""
                    println imageExists

                    if( !imageExists ){
                           sh 'docker stop backend'
                           sh 'docker rm backend'
                           sh 'docker image rm backend'
                    }else {
                        echo 'Skip this stage '
                    }
                }
            }
        }

        stage('remove whole data') {
            steps {
                sh 'rm -rf *'
            }
        }

        stage('git clone') {
            steps {
                git branch: 'master',
                    credentialsId: 'INT222jenkins',
                    url: 'https://github.com/INT222IntegratedProject/back-end.git'
            }
        }

        stage('(deploy) start contianer') {
            steps {
                sh 'docker-compose up -d'
            }
        }

    }
}

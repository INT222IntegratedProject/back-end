pipeline {
    agent any
    stages {

        stage('stop then remove container and image') {
            steps {
                script {
                    def imageExists = sh(script: 'docker images -q backend', returnStdout: true) == ""
                    println imageExists

                    if( !imageExists ){
                           sh 'docker stop backend'
                           sh 'docker rm backend'
                           sh 'docker image rm backend'
                    }else {
                        echo 'This stage does not occur.'
                    }
                }
            }
        }

        stage('remove all the data') {
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

        stage('start contianer') {
            steps {
                sh 'docker-compose up -d'
            }
        }

    }
}

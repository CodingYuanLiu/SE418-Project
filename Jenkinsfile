pipeline {
    agent any
    stages {
        stage('Build') {
            steps {
                sh 'cd ./TongquCrawler'
		sh 'pwd'
		sh 'ls'
                sh 'mvn -B -DskipTests clean package'
            }
        }
        stage('Test') {
            steps {
                sh 'mvn test'
            }
            post {
                always {
                    junit 'target/surefire-reports/*.xml'
                }
            }
        }
        stage('deliver') {
            steps {
                sh './jenkins/scripts/deliver.sh'
            }
        }
    }
}

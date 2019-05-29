pipeline {
    agent any
    stages {
        stage('Build') {
            steps {
                sh 'cd TongquCrawler'
                sh 'mvn -B -DskipTests clean package'
		maven 'mvn ---version'
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

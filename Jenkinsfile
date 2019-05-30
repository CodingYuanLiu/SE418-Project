pipeline {
    agent any
    stages {
        stage('Build') {
            steps {
		sh 'cd project && ./build.sh'
            }
        }
        stage('Test') {
            steps {
                sh 'cd project && ./test.sh'
            }
        }
        stage('deliver') {
            steps {
		sh 'cd project && ./deliver.sh'
            }
        }
    }
}

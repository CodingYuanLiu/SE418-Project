pipeline {
    agent any
    stages {
        stage('Build') {
            steps {
		sh 'cd project && ls && chmod +x ./build.sh &&./build.sh'
            }
        }
        stage('Test') {
            steps {
                sh 'cd project && ls && chmod +x ./test.sh && ./test.sh'
            }
        }
        stage('deliver') {
            steps {
		sh 'cd project && ls && chmod +x ./deliver.sh && ./deliver.sh'
            }
        }
    }
}

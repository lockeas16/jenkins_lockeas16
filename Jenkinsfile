pipeline {
    agent any
    triggers {
        githubPush()
    }
    stages {
        stage('Build') {
            steps {
                echo 'Stage para la construcción'
            }
        }
    }
}
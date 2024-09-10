pipeline {
    agent any
    environment {
        DOCKERHUB_CREDENTIALS = credentials('dockerhubCred')
    }
    tools {
        maven 'Default'
    }
    parameters {
        string defaultValue: 'spc-cd', description: 'Nombre de la aplicación.', name: 'APP_NAME', trim: true
        string defaultValue: '0.1.0', description: 'Tag de la imagen Docker.', name: 'IMAGE_TAG', trim: true
        string defaultValue: 'spc-cd', description: 'Nombre del contenedor de la aplicación construida.', name: 'CONTAINER_NAME', trim: true
    }
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
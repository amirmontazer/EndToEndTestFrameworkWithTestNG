pipeline {
  agent {
    node {
      label 'buildserver'
    }

  }
  stages {
    stage('Build') {
      steps {
        sh 'docker-compose up -d'
        sh 'mvn -T 8 install'
      }
    }
    stage('Test') {
      steps {
        sh 'mvn -T 8 test'
      }
    }
  }
  post {
      always {
          sh 'docker-compose down'
      }
  }
  environment {
    REMOTE_HOST = 'localhost'
    REMOTE_PORT = '4444'
    HOST_URL = 'http://192.168.1.35/'
  }
}

pipeline {
  agent any
  stages {
    stage('build') {
      steps {
        sh 'mvn clean install'
      }
    }

    stage('package') {
      steps {
        sh 'docker build -t processo-industrial-api .'
      }
    }

    stage('tag') {
      steps {
        sh 'docker tag processo-industrial-api:latest srochg/processo-industrial-api'
      }
    }

    stage('push') {
      steps {
        sh 'docker push srochg/processo-industrial-api'
      }
    }

    stage('rollout') {
      steps {
        sh 'kubectl rollout restart deployment tcc-proc-ind-api-deployment'
      }
    }

  }
}
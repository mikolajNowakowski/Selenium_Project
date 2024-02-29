pipeline {
    agent any

    parameters {
  string defaultValue: 'main', name: 'BRANCH', trim: true
}

    stages {
        stage('Git checkout') {
            steps {
                git branch: '${BRANCH}', url: 'https://github.com/mikolajNowakowski/Selenium_Project'
            }
        }

       stage('Performing tests') {
            steps {
                bat 'mvn clean test'
            }
        }

           stage('Generating HTML Report') {
                    steps {
                       publishHTML([allowMissing: false, alwaysLinkToLastBuild: false, keepAll: false, reportDir: '', reportFiles: 'index.html', reportName: 'HTML Report', reportTitles: '', useWrapperFileDirectly: true])
                }
    }
}
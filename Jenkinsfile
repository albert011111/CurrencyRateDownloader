pipeline {
        agent any
        tools {
            jdk 'java8'
            maven 'maven_3_6_1'
        }

        checkstyle {
        ignoreFailures = false
        }

    stages{
            stage('ECHO stage'){
                    steps{                  
                    echo 'stage ECHO'
                    
                    }
            }
            
                    stage('MVN compile'){
                steps{
                        echo 'stage MVN'
                 }
        }
            
        stage('SCM veryfing'){
            steps{
                    echo 'stage SCM'
                    //git 'https://github.com/albert011111/CurrencyRateDownloader'
                    bat 'mvn clean compile'
            }
        }

        stage('Checkstyle'){
                    steps{
                            echo 'stage Checkstyle'
                            bat 'mvn checkstyle:check'
                            recordIssues(tools: [checkstyle(reportEncoding: 'UTF-8')])
                    }
         }
    }
}

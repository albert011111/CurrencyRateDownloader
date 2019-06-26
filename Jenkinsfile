pipeline {
    agent any
    tools {
        jdk 'java8'
        maven 'maven_3_6_1'
    }

    stages {
        stage('Check dependency conflict') {
            steps {
                bat "mvn enforcer:enforce -U"
            }
        }


        stage('SCM veryfing') {
            steps {
                echo 'stage SCM'
                //git 'https://github.com/albert011111/CurrencyRateDownloader'
                bat 'mvn clean package -U checkstyle:checkstyle pmd:pmd pmd:cpd'
            }
        }

        stage('Archive') {
            steps {
                archiveArtifacts artifacts: '/target/*.jar', onlyIfSuccessful: true
            }
        }

        stage('Checkstyle') {
            steps {
                echo 'stage Checkstyle'

                script {
                    def java = scanForIssues tool: [$class: 'Java']
                    def checkstyle = scanForIssues tool: [$class: 'CheckStyle'], pattern: '**/target/checkstyle-result.xml'
                    def pmd = scanForIssues tool: [$class: 'Pmd'], pattern: '**/target/pmd.xml'
                    def cpd = scanForIssues tool: [$class: 'Cpd'], pattern: '**/target/cpd.xml'
                    def findbugs = scanForIssues tool: [$class: 'FindBugs'], pattern: '**/target/findbugsXml.xml'

                    publishIssues issues: [java]
                    publishIssues issues: [checkstyle]
                    publishIssues issues: [pmd]
                    publishIssues issues: [cpd]
                    publishIssues issues: [findbugs]

                    junit testResults: '**/target/surefire-reports/TEST-*.xml'
                }
            }
        }
    }
}

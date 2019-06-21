pipeline {
        agent any
        tools {
            jdk 'java8'
            maven 'maven_3_6_1'
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
                    bat 'mvn clean install -U checkstyle:checkstyle pmd:pmd pmd:cpd'
            }
        }

        stage('Checkstyle'){
                    steps{
                            echo 'stage Checkstyle'
                          //  bat 'mvn checkstyle:check'
                         //   recordIssues(tools: [checkstyle(reportEncoding: 'UTF-8')])
                            
                            script{
                            def checkstyle = scanForIssues tool: [$class: 'CheckStyle'], pattern: '**/target/checkstyle-result.xml'
                            def pmd = scanForIssues tool: [$class: 'Pmd'], pattern: '**/target/pmd.xml'
                            def cpd = scanForIssues tool: [$class: 'Cpd'], pattern: '**/target/cpd.xml'
                            def findbugs = scanForIssues tool: [$class: 'FindBugs'], pattern: '**/target/findbugsXml.xml'
                            
                            publishIssues issues:[checkstyle]
                            publishIssues issues:[pmd]
                            publishIssues issues:[cpd]
                            publishIssues issues:[findbugs]
                            }
                    }
         }
    }
}

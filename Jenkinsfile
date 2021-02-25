pipeline {
    agent any
    options {
        buildDiscarder(logRotator(numToKeepStr: '5'))
        timeout(time: 10, unit: 'MINUTES')
        timestamps()  // Timestamper Plugin
        disableConcurrentBuilds()
    }
    tools {
        oc 'oc4'
    }
    environment {
        APP_LABEL = 'my-app'
        OPENSHIFT_CLUSTER = 'my-cluster'
        OPENSHIFT_CREDENTIALS = 'openshift-jenkins-external'
        OPENSHIFT_PROJECT = 'hannelore11_dev'
    }
    stages {
        stage('oc test') {
            steps {
                println "PATH: ${PATH}"

                println "OC Version from Shell, must be available:"
                sh "oc version"

                println "which oc"
                sh "which oc"
            }
        }
        stage('oc login') {
            steps {
                script {
                    openshift.withCluster(env.OPENSHIFT_CLUSTER) {
                        openshift.withCredentials(env.OPENSHIFT_CREDENTIALS) {
                            openshift.withProject(env.OPENSHIFT_PROJECT) {
                                println "OC Version from Plugin:"
                                println openshift.raw('version').out
                                echo "Hello from project ${openshift.project()} in cluster ${openshift.cluster()}"
                                println openshift.raw('get','project').out
                                println openshift.raw('status').out
                                println openshift.raw('get','pod').out
                            }
                        }
                    }
                }
            }
        }
    }
}
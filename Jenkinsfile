node {

    stage('Clone Repo') {
        git credentialsId: 'airtracking-git', url: 'git@gitlab.com:airtracking/airtracking.gitlab.io.git', branch: 'master'
    }

    stage('Build') {
        sh 'mvn -f airtracking clean package'
        sh 'mvn -f airtrackingwebapp clean package'
    }

    stage('Deploy Artifact') {
        sh 'mvn -f airtracking deploy'
        sh 'mvn -f airtracking deploy'
    }
}
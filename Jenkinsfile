node {

    stage('Clone Repo') {
        git credentialsId: 'airtracking-git', url: 'git@gitlab.com:airtracking/airtracking.gitlab.io.git', branch: 'master'
    }

    stage('Build') {
        sh 'mvn -f airtracking clean package'
        sh 'mvn -f airtrackingwebapp clean package'
    }

    stage('Deploy Artifact') {
        sh 'mvn -s settings.xml -f airtracking deploy'
        sh 'mvn -s settings.xml -f airtrackingwebapp deploy'
    }

    stage('Deploy Runtime') {
        sshagent (credentials: ['airtracking-runtime']) {
            sh 'ssh -o StrictHostKeyChecking=no -l esp52 192.168.160.103 sh pull.sh'
            sh 'ssh -o StrictHostKeyChecking=no -l esp52 192.168.160.103 docker-compose stop'
            sh 'ssh -o StrictHostKeyChecking=no -l esp52 192.168.160.103 docker-compose up -d'
        }
    }
}
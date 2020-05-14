pipeline {
    agent any

    parameters {
        choice choices: ['API & WebApp', 'API', 'WebApp'], description: '', name: 'APPLICATION'
        booleanParam defaultValue: true, description: '', name: 'BUILD'
        choice choices: ['Run Tests', 'Ignore'], description: '', name: 'TESTS'
        choice choices: ['Publish & Deploy', 'Publish', 'Deploy', 'Ignore'], description: '', name: 'ACTION'
    }

    stages {
        stage('Clone Repo') {
            steps {
                git credentialsId: 'airtracking-git', url: 'git@gitlab.com:airtracking/airtracking.gitlab.io.git', branch: 'master'
            }
        }

        stage('Build') {
            when { expression { params.BUILD ==~ /(?i)(Y|YES|T|TRUE|ON|RUN)/ } }
            steps {
                script {
                    if (env.APPLICATION == 'API & WebApp' || env.APPLICATION == 'API') {
                        sh 'mvn -f airtracking clean package -DskipTests'
                    }
                    
                    if (env.APPLICATION == 'API & WebApp' || env.APPLICATION == 'WebApp') {
                        sh 'mvn -f airtrackingwebapp clean package -DskipTests'
                    }
                }
            }
        }

        stage('Tests') {
            when { expression { params.TESTS == 'Run Tests'} }
            steps {
                script {
                    if (env.APPLICATION == 'API & WebApp' || env.APPLICATION == 'API') {
                        echo "Testing"
                    }

                    if (env.APPLICATION == 'API & WebApp' || env.APPLICATION == 'WebApp') {
                        echo "Testing"
                    }
                }
            }
        }

        stage('Publish Artifactory') {
            when { expression { params.ACTION == 'Publish & Deploy' || params.ACTION == 'Publish'} }
            steps {
                script {
                    if (env.APPLICATION == 'API & WebApp' || env.APPLICATION == 'API') {
                        sh 'mvn -s settings.xml -f airtracking deploy -DskipTests'
                    }

                    if (env.APPLICATION == 'API & WebApp' || env.APPLICATION == 'WebApp') {
                        sh 'mvn -s settings.xml -f airtrackingwebapp deploy -DskipTests'
                    }
                }
            }
        }
        
        stage('Publish Docker Registry') {
            when { expression { params.ACTION == 'Publish & Deploy' || params.ACTION == 'Publish'} }
            steps {
                script {
                    if (env.APPLICATION == 'API & WebApp' || env.APPLICATION == 'API') {
                        sh 'docker build -t esp52-airtracking airtracking'
                        sh 'docker tag esp52-airtracking 192.168.160.99:5000/esp52-airtracking'
                        sh 'docker push 192.168.160.99:5000/esp52-airtracking'
                    }
                    
                    if (env.APPLICATION == 'API & WebApp' || env.APPLICATION == 'WebApp') {
                        sh 'docker build -t esp52-airtrackingwebapp airtrackingwebapp'
                        sh 'docker tag esp52-airtrackingwebapp 192.168.160.99:5000/esp52-airtrackingwebapp'
                        sh 'docker push 192.168.160.99:5000/esp52-airtrackingwebapp'
                    }
                }
            }
        }

        stage('Deploy Runtime') {
            when { expression { params.ACTION == 'Publish & Deploy' || params.ACTION == 'Deploy'} }
            steps {
                script {
                    sshagent (credentials: ['airtracking-runtime']) {
                        if (env.APPLICATION == 'API & WebApp' || env.APPLICATION == 'API') {
                            sh 'ssh -o StrictHostKeyChecking=no -l esp52 192.168.160.103 "docker ps -qa --filter name=esp52-airtracking$ --filter status=running | xargs -r docker stop"'
                            sh 'ssh -o StrictHostKeyChecking=no -l esp52 192.168.160.103 "docker ps -qa --filter name=esp52-airtracking$ --filter status=exited | xargs -r docker rm"'
                            sh 'ssh -o StrictHostKeyChecking=no -l esp52 192.168.160.103 "docker images -qa --filter label=name=airtracking | xargs -r docker rmi"'
                            sh 'ssh -o StrictHostKeyChecking=no -l esp52 192.168.160.103 docker run -d -p 9069:8005 --name esp52-airtracking 192.168.160.99:5000/esp52-airtracking'
                        }

                        if (env.APPLICATION == 'API & WebApp' || env.APPLICATION == 'WebApp') {
                            sh 'ssh -o StrictHostKeyChecking=no -l esp52 192.168.160.103 "docker ps -qa --filter name=esp52-airtrackingwebapp$ --filter status=running | xargs -r docker stop"'
                            sh 'ssh -o StrictHostKeyChecking=no -l esp52 192.168.160.103 "docker ps -qa --filter name=esp52-airtrackingwebapp$ --filter status=exited | xargs -r docker rm"'
                            sh 'ssh -o StrictHostKeyChecking=no -l esp52 192.168.160.103 "docker images -qa --filter label=name=airtrackingwebapp | xargs -r docker rmi"'
                            sh 'ssh -o StrictHostKeyChecking=no -l esp52 192.168.160.103 docker run -d -p 9070:8080 --name esp52-airtrackingwebapp 192.168.160.99:5000/esp52-airtrackingwebapp'
                        }
                    }
                }
            }
        }
    }
}
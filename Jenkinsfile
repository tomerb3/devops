def itc_deployment_configs_folder='itc-deployment-config'
def itc_deployment_configs_branch=''
def executingUser=''
def configBuildTagSource=''
def pushedConfigHash=''
def deployment_configs_build_tag=''

pipeline{
  agent {
    node {
     
   }
  }
  options{
    buildDiscarder(
      logRotator(daysToKeepStr: '5', numToKeepStr: '50')
    )
    disableConcurrentBuilds()
  }

parameters {
     extendedChoice( 
        defaultValue: 'none', 
        description: '', 
        multiSelectDelimiter: ',', 
        name: 'Tags', 
        quoteValue: false, 
        saveJSONParameterToFile: false, 
        type: 'PT_CHECKBOX', 
        value:"""
def gettags = ("git ls-remote -t https://github.com/tomerb3/devops.git").execute()
return gettags.text.readLines().collect { 
  it.split()[1].replaceAll('refs/tags/', '').replaceAll("\\^\\{\\}", '')
}
""", 
        visibleItemCount: 3)
  
 
  
    }


  environment{
      def GENERATED_BUILD_TAG=''
  }

  stages{
    stage('parameterBuild'){
      when{ expression{params.SkipRun}}
      steps{
        script{
          echo 'Setting job parameters'
        }
      }
    }
    stage('run'){
      when{ expression{!params.SkipRun}}
      stages{

        stage('checkout jenkinsfile from seed branch'){
          steps{
            script{
              if (env.BUILD_USER){
                  executingUser = env.BUILD_USER
              } else
              {
                  executingUser = params.BUILD_USERNAME
              }
              if (executingUser==null || executingUser==''){
                executingUser=getUserNameFromCause(currentBuild)
              }
              echo "current user name: ${executingUser}"

              itc_deployment_configs_branch="master"
              sh 'env'
          

            }//script
          }//steps
        }//stage checkout

        stage('Configuration Push'){
          steps{
              script{
                dir(itc_deployment_configs_folder){
                  git branch: itc_deployment_configs_branch,  url: 'git@bitbucket.org:observeit/itc-deployment-configs.git'
                 
                  if ( params.SOURCE_COMMIT_HASH_OR_TAG != 'master'){ 
                    commithash_from_tag = sh (script: "git show-ref -s ${SOURCE_COMMIT_HASH_OR_TAG}|| echo NoTagInput",
                      returnStdout: true).trim()
                    if (commithash_from_tag != 'NoTagInput') { 
                      SOURCE_COMMIT_HASH_OR_TAG = commithash_from_tag
                    } 
                    configBuildTagSource = SOURCE_COMMIT_HASH_OR_TAG
                  } else { 
                    configBuildTagSource = sh (script: 'git describe --match "*.*.*" --long HEAD | tr -s "-" "_" | sed "s/_/-/" | sed "s/_g/_/"',  returnStdout: true).trim()
                  }

                  echo 'Value of configBuildTagSource'
                  echo configBuildTagSource
                  deleteDir()
                }

            
          
              }//script
            }//steps
        }//stage configuration push

        stage('Deployment Config Run'){
          steps{
            script{
              echo "running itc-deployment-configs on hash ${pushedConfigHash}"
          
          
            }
          }
        }
      }//run stages
      post{
        success{
          script{
            env.GENERATED_BUILD_TAG=deployment_configs_build_tag
          }
        }
      }
    }//stage run
  }//job stages
}//pipeline

def getUserNameFromCause(currentBuild){
  def userCause
  try{
    userCause=currentBuild.getBuildCauses('hudson.model.Cause$UserIdCause')
    if (userCause==null || userCause.size()<1){
      return ''
    }
    userCause=userCause[0]
  }catch(all){
    return ''
  }
  return userCause.userName!=null?"${userCause.userName}":''
}

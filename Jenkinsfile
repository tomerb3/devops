def itc_deployment_configs_folder='itc-deployment-config'
def itc_deployment_configs_branch=''
def executingUser=''
def configBuildTagSource=''
def pushedConfigHash=''
def deployment_configs_build_tag=''

pipeline{
  agent {
    node {
      label 'linux'
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
        defaultValue: 'One,Two,Three,Four', 
        description: '', 
        multiSelectDelimiter: ',', 
        name: 'SAMPLE_EXTENDED_CHOICE', 
        quoteValue: false, 
        saveJSONParameterToFile: false, 
        type: 'PT_CHECKBOX', 
        value:'One,Two,Three,Four,Five,Six,Seven,Eight,Nine,Ten', 
        visibleItemCount: 10)
    }

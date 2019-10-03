def gettags = ("git ls-remote -t https://github.com/tomerb3/devops.git").execute()
return gettags.text.readLines().collect { 
  it.split()[1].replaceAll('refs/tags/', '').replaceAll("\\^\\{\\}", '')
}

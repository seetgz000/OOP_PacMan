## Please pull before every time work
$ git pull origin master 

## To push what you have done
$ git add .  
$ git commit -m "any message"  
$ git push origin master  

//make sure to add only src directory, as everyone's configuration file might
be different and it will be harder to merge

$ git add src/main

## Create branch for each feature/bugs to fix (update by Ang)
$ git checkout -b (branch name) //create branch

$ git branch -d (branch name) //delere branch

$ git pull (branch name) //receive changes of branch

$ git push (branch name) //push changes of branch

# Jhey-Package-Manager
jpm (Jhey Package Manager) is a CLI tool to add maven dependencies in your projects

# Usage
  ![](https://github.com/seujorgenochurras/Jhey-Package-Manager/blob/main/jpm-showcase.gif) 
  <br>
   ```java -jar <jar name> jpm -i=<dependency name> ``` in a terminal inside your project.<br>
   The jar needs to be in the same folder where the dependency manager file is.
# Why?
  I've always found annoying the way gradle/maven works when dealing with implementing dependencies. <br>
  I never remember the f\*cking implementation string (which is like io.github.google.code.gson:gson:1.0.0)<br><br>
  What I think most of you do is searching in google (*like in stone age*) the implementation string. <br>
  This process ends up taking at minimum 5 seconds (+ downloading and implementing the dependency) <br>
  In other package manager tools (npm and pip for instance) it takes only about 1 second to remember and install the dependency<br>
  My project tries to make this process faster, it automatically searches the dependency name and puts it in the file.

## Alpha 0.5.0
   - Added a loading animation
   - Async web response
   - Way better responses
   - Lighter (totally removed reflections)

## Alpha 0.4.0
   - Refactor whole gradlew mapping
     - From pure regex to a tree based mapping
   - Added more tests
   - Speed
   - Fixed lots of bugs

## Alpha 0.3.0
   - Fixed bug 
      - Updating the reflections library just made my code not work *even thought I wasn't using the library at all*
   - Added Maven pom support
   - Removed reflections 
   - Optimized jpm
   - More stable user directory identification 
   - Cleaner jpm response
   - Spread dirty code >:(

 ## Alpha 0.2.0
   - Added gradle groovy support *GROOVY IMPLEMENTATIONS NEED TO FOLLOW KOTLIN SYNTAX* 
   - Fixed gradle file related bugs
   - Removed a console print


## Alpha 0.1.0
  - Added gradlew kotlin support<br>
  - Added so many errors that this release should be illegal 

   
# Known bugs/defects  
  - Doesn't work with zsh
  - Only works if you've defined a dependency block (gradle/maven)
  - It'll fuck with your tags or close curly braces...
  - Bad code
  - Kinda slow
  - No support for plugins and other implementations such as testImplementation or runtimeOnly
  - Lacking tests
  - No auto-update/update command
  - No error checking (if something goes wrong you are receiving the whole stack trace)
  - Primitive af



# Current Project State
  It has been a long time since I worked on this project.
  And like most of open source projects I have burnt out of this one. <br><br>
  
  Although I had the most fun while coding it, this project have many design flaws 
  such as lack of tests, no error handling pattern, and not counting all of the bugs and broken features.<br>
  Even though it is quite broken, I'm still using JPM on almost all of my projects, and deep inside I do recommend you try it out. <br>
  I still want to keep developing JPM as it has accomplished some of its goals.<br><br>

  There are some features that I want to add such as: 
  - Add a .toml like file to store dependencies
  - Add tests
  - Add a error handling pattern
  - Uploading JPM to linux packages handlers such as apt or pacman 
  - Add docs

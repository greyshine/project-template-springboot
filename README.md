# PROJECT TEMPLATE FOR SPRINGBOOT

includes the [google material icon](https://google.github.io/material-design-icons/) set   
includes [jquery](https://jquery.com/)  
includes [purecss.io](https://purecss.io)  

Run maven and package. A jar artefact is being build which is simply startable by

- run the main  
  ``de.greyshine.springboottemplate.Main [--server.port=xyz]``  
    
     
- run as executable java  
  ``java -jar target/project-template-springboot-0.0.1-SNAPSHOT.jar [--server.port=xyz]`` 
  
then enter into the browser as address: ``localhost:<port>``

Shows different usages for presenting outputs (html, json, plain/text, ...):
- generic output of static files, e.g. [http://localhost:8080/index.html](http://localhost:8080/index.html) 
- forward of a resource via controller method [http://localhost:8080/index2.html](http://localhost:8080/index2.html) (Index2Controller.show())
- plain text output: [http://localhost:8080/status](http://localhost:8080/index2.html)
- templating thymeleaf-example: [http://localhost:8080/thymeleaf-example](http://localhost:8080/thymeleaf-example)


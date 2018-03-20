## PCF - Learning ##

### How to create a sample project and deploy to PCF ###
- Note: Here I am using gradle and Spring boot framework to build the project and deloy to PCF
- Create a Gradle project using [Spring Initializr](https://start.spring.io/)
- Select Generate Gradle Project with Java and select springboot web module and create a base project
- Create a Sample Controller
```
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {

    @GetMapping("/")
    public String sayHello() {
        return "hello";
    }
}
```
- Run the project and test in [local](http://localhost:8080/), it will display a get response with message hello

- Steps to create cf account and access cf space
	- install CF CLI on you system using [link](https://docs.cloudfoundry.org/cf-cli/install-go-cli.html)
	- create PCF free account using [link](https://account.run.pivotal.io/z/uaa/sign-up)
	- Login to cf cloud using command line, it will ask email and password for ur pcf account
```
	cf login -a https://api.run.pivotal.io
```
- Steps to deploy code to pcf
	- run gradle build from intellij IDE or run ./gradlew clean build in the project folder
	- push your code to pcf by using cf push command
```
	cf push basedemo -p build/libs/basedemo-0.0.1-SNAPSHOT.jar --random-route
```
	- after you push your project, it will give random url for access your application
	- go and access the url; should display same result as our local system


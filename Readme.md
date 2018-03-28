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

	- after you push your project, it will give random route url for access your application
	- go and access the url; should display same result as our local system
 [commit changes](https://github.com/dvinay/PCF-learning/commit/e150764c52dcb19775055d0104ae8906bef76435)

- Design and development rules for developing Service oriented application [link](https://12factor.net/)

### Configure application depending on env properties ###
- While developing cloud based application, sometimes we need to depend on the env configuration. These configurations will useful to make application work in different env with same functionality
- In springboot, by using @Value("${WELCOME_MESSAGE:DEFAULT_VALUE}") expression to fetch env variables
- PCF provides come set of env properties like PORT, DATABASE_URL etc [link](http://docs.run.pivotal.io/devguide/deploy-apps/environment-variable.html)

### Ways to configure env variables ###
1. Configuring env variables while deploying/running application
	- If we want to configure WELCOME_MESSAGE env variable while running application, add the variable at running time
``` 
$WELCOME_MESSAGE=hello ./gradlew bootRun 
```

2. Configuring env variable in build management configuration files like pom.xml or build.gradle
	- If we want configure WELCOME_MESSAGE env variable for different env like test or stg
```
bootRun.environment([
 "WELCOME_MESSAGE": "hello",
])

test.environment([
    "WELCOME_MESSAGE": "Hello from test",
])
```

3. Configuring env variable in application properties configuration files like application.properties or application.yaml
```
WELCOME_MESSAGE=from application properties
```

4. Configuring custom env variable in PCF by using set-env command
```
$ cf set-env application_name WELCOME_MESSAGE "Hello from Cloud Foundry"
```

- Note:
	- use first approaches if your application want change application behaviour on different env
	- use second and third approaches if your application want depend on application, this is not preferable for saving security properties like JWT key or passwords



























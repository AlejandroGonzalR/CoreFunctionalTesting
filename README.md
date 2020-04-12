# Core Functional Testing with Selenium

Selenium functional test cases for simple staging web app. Please check the web app following this [link](https://github.com/AlejandroGonzalR/jenkins-vue) or [vue-simple-form-staging.web.app](https://vue-simple-form-staging.web.app/)

## Getting Started

This project is built with maven, to start import the changes of the POM file. After compile or building the artifact, you can run the tests using the command:

```
mvn test
```

You can also run each test individually, reports are generated in the **/test-output** directory.

Notes:

* You can change the build and test paths editing the [Constants](https://github.com/AlejandroGonzalR/CoreFunctionalTesting/blob/master/src/main/java/Constants.java) file.
* This project contains the WebDrivers of Chrome and Firefox for linux, you can find the WebDrivers you need by looking in the Selenium [documentation](https://www.selenium.dev/documentation/en/webdriver/driver_requirements/).
* This project contains a Jenkins DSL jobs to test the construction and execution of the tests, to use the Job please use the hub containers and the nodes of each browser from the [Selenium Docker account](https://hub.docker.com/u/selenium) in the Jenkins DSL plugin.

## Built With

* [Maven](https://maven.apache.org/) - Dependency Management

## Authors

* **Alejandro González Rodríguez** - *Initial work*

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details

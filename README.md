# Nishant Thakur Assignment

### Description ###
Test projects to validate NewUser and AllUser Page:

**Dependencies** 
- Java8
- Maven3
- Junit5
- Selenide
- RestAssured


#### Important Files/Packages:
- **src/java/dto/UserDto.java** : User info POJO 
- **src/java/page/** : Package for Page classes. Uses Selenide to create Page Object Model 
- **src/java/util/** : Package for utility classes. 
- **src/test/java/test/ui/unit/** : Package for Test classes for static checks on elements of NewUser and AllUser Page 
- **src/test/java/test/ui/usecase/** : Package for Test classes for user flow tests 
- **src/test/resources/test.properties** : Defines default values for test execution. Like browser and grid url. testng.xml parameters are given precedence.

#### test.properties
| ParamName | Value |
| -------- | ----------- |
| browser.name | browser to use |
| base.url | application base url  |

#### Testing
##### Via Commandline
- Open terminal and go to project root folder.
- run **mvn clean test**

##### Run tests Via JUnit plugin in Eclipse/IntelliJ
- Direclty run Test class or Test method as reuired in run or debug mode by right clicking on class or method name and choose run

# Master Springboot. My lecture 😉
 
  1. how to find or delete one value or relaled 
  if normal code use loop for list value by loop use duplicate check 
  but new pactice is predicate by predicate is filter not loop for find value 
  
  ##Exam code
loop
 ```
        for ( Iterator<User> userIterator = users.iterator(); userIterator.hasNext();){
               User user = userIterator.next();
               if(user.getId() == id){
                    userIterator.remove();
                    break;
               }
           }
```  
predicate
```       
Predicate<?super User> predicate = user -> user.getId() == id;
users.removeIf(predicate);            
```

2. UX in response api to user by if user create api by return Response http status and location path in header.

```
    @PostMapping("/user")
    public ResponseEntity<User> saveUser(@RequestBody User user){
        userDAO.saveUser(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(location).build();
    }
```

## api document 
 springdoc-openapi Dependency 
 
 ```
 <dependency>
	<groupId>org.springdoc</groupId>
	<artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
	<version>2.0.0</version>
</dependency>
 ```
 when install re maven and open url
 
 ```
 http://localhost:8080/swagger-ui/index.html
 ```
 
 ## if employee need format data default is json but if need xml mush add depedency
 ```
 <dependency>
   <groupId>org.springdoc</groupId>
   <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
   <version>2.0.0</version>
</dependency>
```

and add parameter  
![image](https://user-images.githubusercontent.com/97660202/229691634-6c2e3e86-0749-4751-bdf8-bbce2443f3a2.png)

## hateoas (HAL Hyper Application Language)
	hateoas is config identify link api for ux give easy understand
	
```
	<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-hateoas</artifactId>
		</dependency>

```

Exam code

```
    @GetMapping(path = "user/hateoas/{id}")
    public EntityModel<User> userEntityModelPerson1(@PathVariable int id) {
        EntityModel entityModel = EntityModel.of( userDAO.findOneUser(id));
        WebMvcLinkBuilder linkFindAllUser = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).findALlUser());
        WebMvcLinkBuilder linkHelloBean = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).HelloBean());
        WebMvcLinkBuilder linkDeleteMyUser = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).deleteUser(id));

        entityModel.add( linkFindAllUser.withRel("all-user").withTitle("title all users") , linkHelloBean.withRel("hello-bean") , linkDeleteMyUser.withRel("delete-user").withTitle("delete user") );
        return entityModel;
    }
```

![image](https://user-images.githubusercontent.com/97660202/230371815-c4aa6e44-7a28-4b63-b1f8-0715d8c6db30.png)


## Filtering have 2 type is staic and dynamic filtering 
static filterinig is config output json ? so static filtering use json ignore (level attribute) and json ignore property (level class)
 - json property is rename key name of the coloum.

![image](https://user-images.githubusercontent.com/97660202/230543522-c9dbcf3e-c884-4fb0-aac3-77094f0b5cd0.png)
![image](https://user-images.githubusercontent.com/97660202/230543559-978df6a2-73ee-4799-90a4-949871f2273e.png)
![image](https://user-images.githubusercontent.com/97660202/230543599-7c5c7ea7-d097-42d5-a7ff-4f2da169030d.png)

Dynamic filter (Json filter)
-	Mapping json by use service is parameter 
-	Filter provider config by key json filter and filter json parameter 
-	Filter provider have 3 type is filter out except , serialize except and serialize all
![image](https://user-images.githubusercontent.com/97660202/230551766-5a835e58-8fed-44ea-ab5a-1d515010edeb.png)
![image](https://user-images.githubusercontent.com/97660202/230551794-bffd4e97-64b7-4f44-9b5b-15e47b908105.png)


## Monitor API by actuator (http://localhost:8080/actuator)

```
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-actuator</artifactId>
		</dependency>
```

if want unlock all api insert my code in application.property
```
management.endpoints.web.exposure.include=*  
```

![image](https://user-images.githubusercontent.com/97660202/230554188-c76e417a-fd8c-4cc2-afd4-40f4dd2b9751.png)


## Hal Explorer 
 - it is UI of API by easy using and same swagger but easy more that swagger
 - but explorer use for see link if use hateoas config link for user understance that what can use api ?
Solution install Hal Explorer 

```
<dependency>
			<groupId>org.springframework.data</groupId>
			<artifactId>spring-data-rest-hal-explorer</artifactId>
		</dependency>
```
link http://localhost:8080/explorer/index.html#

![image](https://user-images.githubusercontent.com/97660202/230557427-6b2c6ae8-1d46-4e47-a0dc-625a040484dd.png)


## spring boot basic security 
	- when run program will be login interface give username and password

```
<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-security</artifactId>
		</dependency>
```

if not config in application property 
 - default username is user 
 - default password is terminal
 ![image](https://user-images.githubusercontent.com/97660202/230560860-74796672-9b06-4eeb-a8e1-a87aa379ce94.png)

but if config in application property 
```
spring.security.user.name= admin
spring.security.user.password= admin
```
 - user and password is that config in application property.

![image](https://user-images.githubusercontent.com/97660202/230561482-3e0b5297-1fe7-4527-90ac-cf37d6194131.png)








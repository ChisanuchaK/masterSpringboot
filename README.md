﻿# masterSpringboot
 
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

## hateoas 
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
    ![image](https://user-images.githubusercontent.com/97660202/230371561-81d54b8a-83ef-4473-901f-a9d64275f0eb.png)

    


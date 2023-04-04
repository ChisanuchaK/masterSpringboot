# masterSpringboot
 
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


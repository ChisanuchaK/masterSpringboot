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
            

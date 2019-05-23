# Ribbon 
## How to check
> ./run.sh

This will run the ``mvn clean package``, and ``java -jar``. </br
NOTE: THe script will run four instances in the background. One is ``service discovery``, another one is 
``service-consumer``, the rest are ``service-provider``
## How to check
> type on your browser localhost:8800
After that, you will see eureka page. and there should be three instances registered already.
### To visit consumer
> localhost:9000/activities/{id}
id can be 1 or 2, other id will be ignored

## Our load balanced strategy
```java

```

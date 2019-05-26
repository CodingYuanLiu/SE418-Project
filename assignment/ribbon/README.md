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
> WeightedRoundRobinRule<br/>

Fake weighted RoundRobinRule. 
Our idea is to improve the base roundRobinRule. Key idea is ``maxResponseTime`` and each 
``responseTime`` of single server. ``weight`` is the (maxResponseTime + 1) / (responseTime + 1).

For example :<br/>
ResponseTime for A is 0.5s, and maxResponseTime in the all Server list is 1s.
Then weight for A is 2. During the access to server group, it will access A twice and arter that, 
move on the the next server.




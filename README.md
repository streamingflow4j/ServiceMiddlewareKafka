# About this project (ServiceMiddlewareKafka)
This project is a contextual middleware made with java, Spring Integration and EsperCEP.

## 1. Running the Kafka project
Install and run kafka. More informations(https://kafka.apache.org/quickstart).

## 2. Guide install
1. Download the project file
2. Unzip the project file
3. Configure kafka.properties in src/main/resources:
- queue.streaming.data=si.test.queue (Queue of data streming)
- queue.rule.cep=si.ceprule.queue (Queue of data rules)
4. Build the project using mvn clean install
5. Run it using `java -cp target/ServiceMiddlewareCEPKafka.jar com.service.middleware.main.MainServiceMiddlewareKafka`

## 3. API examples (Monitoring Temperature):
Sending message to kafka queues with the following scope.

Execute RestEndpointKafka Spring Boot application


### API elements
- ADD_EVENT ```add event type, every before specific rule```
- RULECEP   ```define addiction of a rule```
- RULE ```indicate the QUERY to be add(EX.: select temperature from Termometer.win:time(5 sec))``` 
-	QUEUE ```define destination queue of rule trigger```
- EDIT_RULECEP ```define EDITION of a rule```	
- RULE_ID ```ID of rule to be Edited/Deleted```
- DEL_RULE ```command to delete rule```

a) Defining a context element entity for event types:

-- post Kafka endpoint: </endpoint-address-uri>/kafka/event/create
-- post Body:
```
{
"type" : "ADD_EVENT",
"id" : "Termometer",
"attributes" : [
{ 
"name"  : "id",
"type"  : "String",
"value" : "0"
},
{
"name" : "temperature",
"type"  : "Double",
"value" : "0"
}
]
}
```
b) Creating context rules for event types:

-- post Kafka endpoint: </endpoint-address-uri>/kafka/rule/create
-- post Body:
```
{
"type" : "RULECEP", 
"id" : "Rule7",
"attributes" : [
{ "name"  : "RULE",   --Attribute for rule definition
"type"  : "String",
"value" : "select temperature from Termometer.win:time(5 sec)"
},
{
"name"  : "QUEUE_1",
"type"  : "QUEUE",         --Attribute for destination queue of rule outcomes
"value" : "si.cep.queue" 
}
]
}
```
c) Updating context rules for event types:

-- put Kafka endpoint: </endpoint-address-uri>/kafka/rule/update
-- put Body:
```
{
"type" : "EDIT_RULECEP",
"id" : "Rule3",
"attributes" : [
{ 
"name"  : "RULE_ID",   --Obs.: rule id can be obtained in the logfile.
"type"  : "String",
"value" : "f9c9c1c1-d1ed-4f03-99cb-7083533ac2e0"
},
{
"name"  : "RULE",    --Attribute for rule update
"type"  : "String",  
"value" : "select temperature from Termometer.win:time(10 sec)"
},
{
"name"  : "QUEUE_1",
"type"  : "QUEUE",        --You can change destination queue or else keep the same
"value" : "si.cep.queue"
}
]
}
```

d) Delete a rule defined:

-- delete Kafka endpoint: </endpoint-address-uri>/kafka/rule/delete
-- post Body:
```
{
"type" : "DEL_RULE",
"id" : "DelRule3",
"attributes" : [
{
"name"  : "RULE_ID",
"type"  : "String",
"value" : "d081f1bc-4d68-4427-bd47-59684e480327"
}
]
}
```

e) Scope of data producer:

-- post Kafka endpoint: </endpoint-address-uri>/kafka/data/create
-- post Body:
```
{
"type" : "Termometer",
"id" : "1",
"attributes" : [
{ 
"name" : "temperature",
"type" : "Double",
"value" : "2"
}
]
}
```

f) Scope of data Consumer:

```
{
"type" : "EventCEP",
"id" : "1582033487619",
"attributes" : [
{
"name":"temperature",
"type" :"String",
"value":"13.0"
}
]
}
```

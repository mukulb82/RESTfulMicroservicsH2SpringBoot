# RESTfulMicroservicsH2SpringBoot
CRUD operations using in-memory H2 DB build using SpringBoot Restful microservices

1) Create persons:


HTTP method:POST: 

URL: http://localhost:8080/persons

Body

[{
			"ssn" : "1122",
            "first_name": "John",
			"last_name": "Keynes",
			"age": "29",
			"favourite_colour": "red"
		},
		{
            "ssn" : "1123",
			"first_name": "Sarah",
			"last_name": "Robinson",
			"age": "54",
			"favourite_colour": "blue"
		},
		{
        "ssn": "1125",
        "first_name": "Mukul",
        "last_name": "Keynes",
        "age": "29",
        "favourite_color": null
    }
	]
	
Output:

{
    "code": "200",
    "message": "Persons saved successfully"
}

======================================================

2) Get persons

URL:  http://localhost:8080/persons

HTTP method: GET
================

Output:

[{
			"ssn" : "1122",
            "first_name": "John",
			"last_name": "Keynes",
			"age": "29",
			"favourite_colour": "red"
		},
		{
            "ssn" : "1123",
			"first_name": "Sarah",
			"last_name": "Robinson",
			"age": "54",
			"favourite_colour": "blue"
		},
		{
        "ssn": "1125",
        "first_name": "Mukul",
        "last_name": "Keynes",
        "age": "29",
        "favourite_color": null
    }
	]
	


3) Update a person

http://localhost:8080/persons

HTTP method: PUT


Output:

{
    "code": "200",
    "message": "Person updated successfully"
}





4) Delete a person by SSN:


URL:: http://localhost:8080/persons/1126
HTTP method: Delete

Output:: (Returns count of record deleted.)

1





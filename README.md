# MutantDnaValidate
Este proyecto contine una lambda de aws con las siquientes capacidades en el handler

validar una cadena de DNA suminsitarda en un Strin[] para identificar si cumple con la condiciones para identificar aun mutante
el string[] representa una matriz de nxn

1. String[] dna = {"ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"};

condicion a evaluar :
	Sabrás si un humano es mutante, si encuentras más de una secuencia de cuatro letras
	iguales, de forma oblicua, horizontal o vertical.
	
el metodo responde json de la clase Response data con la siguiente informacion 

caso afirmativo (es mutante)
	
		{
			"statusCode": 200,
			"body": {
				"message": "DNA Mutante"
			}
		}
		
caso negativo:NO es mutante
	
		{
			"statusCode": 403,
			"body": {
				"message": "DNA Humano"
			}
		}
		
error en los datos de entrada

		{
			"statusCode": 403,
			"body": {
				"message": "error dataInput"
			}
		}
		
		
2.  Por cada cadena de DNA analizada se agraga un registo a una tabla en Dynamodb con los siguientes datos

	id: valor int que se genera aleatoriamente
	
	ismutant: campo tipo cadena con calor "true" o "false" segun el resultado de la validacion 
		
**Esta funcionalidad esta desplegada en en AWS mediante un metodo GET en la siguiente url**

https://g328gu1tgh.execute-api.us-east-1.amazonaws.com/qa/mutant

En este repositorio encontarás  una colección de postman con el nombre **mercadoLibreCollection** que te permitirá realizar peticiones a la ruta de aws		

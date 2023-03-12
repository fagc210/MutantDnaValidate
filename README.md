# MutantDnaValidate
Es proyecto contine una lambda de aws con las siquientes capacidades en el handler

validar una cadena de DNA suminsitarda en un Strin[] para identificar si cumple con la condiciones para identificar aun mutante
el string[] representa una matriz de nxn

String[] dna = {"ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"};
condicion a evaluar :
	Sabrás si un humano es mutante, si encuentras más de una secuencia de cuatro letras
	iguales, de forma oblicua, horizontal o vertical.
	
el metodo responde json de la clase Response data con la siguiente informacion 

	caso afirmativo (es mutante)
	
		{
			"statusCode": 200,
			"body": {
				"message": "todo bien"
			}
		}
		
	caso negativo:NO es mutante
	
		{
			"statusCode": 403,
			"body": {
				"message": "error"
			}
		}
		
Esta fincionalidad esta desplegada en en AWS mediante un metodo post en la siguiente url

https://g328gu1tgh.execute-api.us-east-1.amazonaws.com/qa/mutant

en es repositorio encontar una coleccion de postman que te permitira realizar peticiones a la ruta de aws		

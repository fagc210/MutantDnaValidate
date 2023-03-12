package com.mercadolibre;


import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class MutantDnaValidateLambda implements RequestHandler<Dna,String> {

    private AmazonDynamoDB amazonDynamoDB;

    @Override
    public String handleRequest(Dna dna, Context context) {
        this.initDynamoDbClient();
        DynamoDBMapper mapper = new DynamoDBMapper(amazonDynamoDB);
        context.getLogger().log("DNA:"+dna);
        DnaValidateUseCase dnaValidateUseCase = new DnaValidateUseCase();
        boolean isMutant = dnaValidateUseCase.isMutant(dna.getDna());
        if(isMutant){
            MutantTableDB mutantTableDB = new MutantTableDB();
            mutantTableDB.setId(String.valueOf((int)(Math.random()*10+1)));
            mutantTableDB.setIsmutant("true");
            mapper.save(mutantTableDB);
            return "es mutante";
        }else{
            MutantTableDB mutantTableDB = new MutantTableDB();
            mutantTableDB.setId(String.valueOf((int)(Math.random()*10+1)));
            mutantTableDB.setIsmutant("false");
            mapper.save(mutantTableDB);
            return "es no mutante";
        }
    }

    private void initDynamoDbClient() {
        this.amazonDynamoDB = AmazonDynamoDBClientBuilder.defaultClient();
    }
}

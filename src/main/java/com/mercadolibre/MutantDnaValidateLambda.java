package com.mercadolibre;


import com.amazonaws.Response;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class MutantDnaValidateLambda implements RequestHandler<Dna,ResponseData> {

    private AmazonDynamoDB amazonDynamoDB;

    @Override
    public ResponseData handleRequest(Dna dna, Context context) {
        this.initDynamoDbClient();
        DynamoDBMapper mapper = new DynamoDBMapper(amazonDynamoDB);
        context.getLogger().log("DNA:"+dna);
        DnaValidateUseCase dnaValidateUseCase = new DnaValidateUseCase();
        if(dnaValidateUseCase.validateInputData(dna.getDna())){
            boolean isMutant = dnaValidateUseCase.isMutant(dna.getDna());
            if(isMutant){
                MutantTableDB mutantTableDB = new MutantTableDB();
                mutantTableDB.setId(String.valueOf((int)(Math.random()*1000+1)));
                mutantTableDB.setIsmutant("true");
                mapper.save(mutantTableDB);
                return ResponseData.builder()
                        .statusCode(200).body(BodyResponse.builder()
                                .message("DNA Mutante").build()).build();

            }else{
                MutantTableDB mutantTableDB = new MutantTableDB();
                mutantTableDB.setId(String.valueOf((int)(Math.random()*1000+1)));
                mutantTableDB.setIsmutant("false");
                mapper.save(mutantTableDB);
                return ResponseData.builder()
                        .statusCode(403).body(BodyResponse.builder()
                                .message("DNA Humano").build()).build();
            }
        }else {
            return ResponseData.builder()
                    .statusCode(403).body(BodyResponse.builder()
                            .message("error dataInput").build()).build();
        }

    }

    private void initDynamoDbClient() {
        this.amazonDynamoDB = AmazonDynamoDBClientBuilder.defaultClient();
    }
}

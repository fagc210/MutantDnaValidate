package com.mercadolibre;


import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName = "mutantdb-qa")
public class MutantTableDB {
    @DynamoDBHashKey
    private String id;

    @DynamoDBAttribute
    private String ismutant;

    public MutantTableDB() {
    }

    public MutantTableDB(String id, String ismutant) {
        this.id = id;
        this.ismutant = ismutant;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIsmutant() {
        return ismutant;
    }

    public void setIsmutant(String ismutant) {
        this.ismutant = ismutant;
    }
}


package com.mercadolibre;

import java.util.Arrays;

public class DnaValidateUseCase {
    public boolean isMutant(String[] dna){
        System.out.println("========   analizando DNA ==============");
            String[][] matrix = convertArrayString2Matrix(dna);
            int vertical=findVertical(matrix);
            int horizontal=findHorizontal(matrix);
            int oblique=findOblique(matrix);
            //printMatrix(matrix);
            System.out.println("vertical="+vertical);
            System.out.println("horizontal="+horizontal);
            System.out.println("oblique="+oblique);
            if((vertical+horizontal+oblique)>=2){
                return true;
            }else {
                return false;
            }
    }

    public  void printMatrix(String[][] matrix){
        for (int i = 0; i < matrix.length; i++) {
            String data="";
            for (int j = 0; j < matrix.length ; j++) {
                data=data+matrix[i][j];
                data=data+" ";
            }
            System.out.println(data);

        }
    }

    /**
     * Convierte un array de string de nxn en una arreglo bidimension(matriz) de nxn
     * @param dna
     * @return la matriz
     */
    public String[][] convertArrayString2Matrix( String[] dna){
        String[][] matrix = new String[dna.length][dna.length];
        for (int i = 0; i < dna.length; i++) {
            String s = dna[i];
            for (int j = 0; j < dna.length; j++) {
                matrix[i][j] = s.substring(j,j+1);
            }
        }
        return matrix;
    }

    /**
     * busca si de forma oblicua si se tienen secuencias de mas de 4 letras iguales
     * @param matrix
     * @return el numero de secuencias encontradas
     */
    public int findOblique(String[][] matrix){
        int oblique = 0;
        int cont =0;
        for (int i = 0; i < matrix.length-1; i++) {
            if(matrix[i][i].equals(matrix[i+1][i+1])){
                cont++;
                if(cont==3){
                    oblique++;
                }
            }else{
                cont=0;
            }
        }
        return oblique;
    }

    /**
     * busca si de forma vertical si se tienen secuencias de mas de 4 letras iguales
     * @param matrix
     * @return el numero de secuencias encontradas
     */
    public int findVertical(String[][] matrix){
        int vertical = 0;
        for (int j = 0; j < matrix.length; j++) {
            int cont =0;
            for (int i = 0; i < matrix.length-1 ; i++) {
                if(matrix[i+1][j].equals(matrix[i][j])){
                    cont++;
                    if(cont==3){
                        vertical++;
                    }
                }else{
                    cont=0;
                }
            }
        }
        return vertical;
    }

    /**
     * busca si de forma horizontal si se tienen secuencias de mas de 4 letras iguales
     * @param matrix
     * @return el numero de secuencias encontradas
     */
    public int findHorizontal(String[][] matrix){
        int horizontal = 0;

        for (int i = 0; i < matrix.length; i++) {
            int cont =0;
            for (int j = 0; j < matrix.length-1 ; j++) {
                if(matrix[i][j].equals(matrix[i][j+1])){
                    cont++;
                    if(cont==3){
                        horizontal++;
                    }
                }else{
                    cont=0;
                }
            }
        }
        return horizontal;
    }

    /**
     * validacion de cumplimiento de matiz nxn y que Las letras de los Strings solo pueden ser: (A,T,C,G),
     * @param dna
     * @return booleano true indicando si cumple con las dos condiciones o false en caso contrario
     */
    public boolean validateInputData(String[] dna) {
        if (dna.length < 4) {
            return false;
        } else {
            long resultValidation = Arrays.stream(dna)
                    .filter(x -> (x.matches("[ATCG]+") && (x.length() == dna.length))).count();
            if (dna.length != resultValidation) {
                System.out.println("la data de DNA no cumple con las caracteristicas");
                return false;
            }
        }
        return true;
    }

}

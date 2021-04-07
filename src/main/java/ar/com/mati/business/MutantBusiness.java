package ar.com.mati.business;

import ar.com.mati.business.exception.BusinessException;
import ar.com.mati.model.DnaDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class MutantBusiness implements IMutantBusiness {

    @Override
    public boolean isMutant(DnaDTO DNAString) throws BusinessException {
        int size = DNAString.getDna().length;
        char[][] dna = new char[size][size];
        for (int i = 0; i < size; i++) {
            dna[i] = DNAString.getDna()[i].toCharArray();
        }
        char[][] transpose = transpose(dna);
        char[][] antiTranspose = antiTranspose(dna);
        if (searchHorizontalAndVerticalMutant(dna, transpose) || searchDiagonalMutant(dna, transpose, antiTranspose)) {
            return true;
        }
        return false;
    }

    public boolean searchHorizontalAndVerticalMutant(char[][] DNAString, char[][] DNATranspose) {
        int size = DNAString.length;
        for (int i = 0; i < size; i++) {
            if (searchRepeatedCharacters(DNAString[i])) {
                return true;
            }
            if (searchRepeatedCharacters(DNATranspose[i])) {
                return true;
            }
        }
        return false;
    }

//    public boolean searchVerticalMutant(char[][] DNAString) {
//        char[][] matrixT = transpose(DNAString);
//        return searchHorizontalMutant(matrixT);
//    }

    public boolean searchDiagonalMutant(char[][] DNAString, char[][] DNATranspose, char[][] DNAAntiTranspose) {
        if (upperDiagRight(DNAString, DNATranspose, DNAAntiTranspose) || upperDiagLeft(DNAString)) {
            return true;
        }
        return false;
    }

    public boolean upperDiagRight(char[][] matrix1, char[][] matrix2, char[][] matrix3) {
        int size = matrix1.length;
        for (int i = 0; i < size - 3; i++) {
            String finalString1 = "";
            String finalString2 = "";
            String finalString3 = "";
            for (int j = 0; j < size - i; j++) {
                finalString1 += matrix1[j][j + i];
                finalString2 += matrix2[j][j + i];
                finalString3 += matrix3[j][j + i];
            }
            char[] dna1 = finalString1.toCharArray();
            char[] dna2 = finalString2.toCharArray();
            char[] dna3 = finalString3.toCharArray();
            if (searchRepeatedCharacters(dna1)) {
                return true;
            }
            if (searchRepeatedCharacters(dna2)) {
                return true;
            }
            if (searchRepeatedCharacters(dna3)) {
                return true;
            }
        }
        return false;
    }

//    public boolean lowerDiagRight(char[][] matrix) {
//        for (int i = 0; i < matrix.length - 3; i++) {
//            String finalString = "";
//            for (int j = 0; j < matrix.length - i; j++) {
//                finalString += matrix[j + i][j];
//            }
//            char[] dna = finalString.toCharArray();
//            if (searchRepeatedCharacters(dna)) {
//                return true;
//            }
//        }
//        return false;
//    }

    public boolean upperDiagLeft(char[][] matrix1) {
        int size = matrix1.length;
        for (int j = 0; j < size - 3; j++) {
            String finalString1 = "";
            for (int i = 0; i < size - j; i++) {
                finalString1 += matrix1[(size - 1) - i - j][i];
            }
            char[] dna1 = finalString1.toCharArray();
            if (searchRepeatedCharacters(dna1)) {
                return true;
            }
        }
        return false;
    }

    public boolean searchRepeatedCharacters(char[] string) {
        int size = string.length;
        int cont = 0;
        char lastCharacter = '-';
        for (int j = 0; j < size; j++) {
            if (lastCharacter == '-') {
                lastCharacter = string[j];
            } else {
                if (lastCharacter == string[j]) {
                    cont++;
                } else {
                    lastCharacter = string[j];
                    cont = 0;
                }
                if (cont == 3) {
                    return true;
                }
            }
        }
        return false;
    }

    public char[][] transpose(char[][] DNAString) {
        int size = DNAString.length;
        char[][] matrixT = new char[size][size];
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < DNAString[x].length; y++) {
                matrixT[y][x] = DNAString[x][y];
            }
        }
        return matrixT;
    }

    public char[][] antiTranspose(char[][] matrix) {
        int size = matrix.length;
        char[][] matrixT = new char[size][size];
        char temp;
        for (int i = 0; i < size - 1; i++) {
            for (int j = 0; j < size - 1 - i; j++) {
                temp = matrix[j][i];
                matrixT[j][i] = matrix[size - 1 - i][size - 1 - j];
                matrixT[size - 1 - i][size - 1 - j] = temp;
            }
        }
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size / 2; j++) {
                temp = matrix[j][i];
                matrixT[j][i] = matrix[size - 1 - j][i];
                matrixT[size - 1 - j][i] = temp;
            }
        }
        return matrixT;
    }

    public void printMatrix(char[][] matrix) {
        System.out.println("######################################################");
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                System.out.printf("" + matrix[i][j]);
            }
            System.out.println();
        }
        System.out.println("######################################################");
    }
}

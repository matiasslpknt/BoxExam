package ar.com.mati.business;

import ar.com.mati.business.exception.BusinessException;
import ar.com.mati.model.DnaDTO;
import org.springframework.stereotype.Service;

/**
 * This class basically determines if a DNA chain is mutant or not
 * @author: Matias Augusto Manzanelli
 * @see <a href = "https://github.com/matiasslpknt/BoxExam.git" />
 */

@Service
public class MutantBusiness implements IMutantBusiness {

    /**
     * Determines if a DNA chain is mutant or not
     * @param DNAString has a list of DNA chains
     * @return boolean true if is mutant, false if not
     */
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

    /**
     * Searches vertical and horizontaly if the DNA matrix is mutant or not
     * @param DNAString DNA matrix
     * @param DNATranspose DNA transpone matrix
     * @return boolean true if is mutant, false if not
     */
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

    /**
     * Searches in all permited DNA matrix diagonals if its mutant or not
     * @param DNAString DNA matrix
     * @param DNATranspose DNA transpone matrix
     * @param DNAAntiTranspose DNA antitranspone matrix
     * @return boolean true if is mutant, false if not
     */
    public boolean searchDiagonalMutant(char[][] DNAString, char[][] DNATranspose, char[][] DNAAntiTranspose) {
        if (leftToRightDiagonal(DNAString, DNATranspose, DNAAntiTranspose) || rightToLeftDiagonal(DNAString)) {
            return true;
        }
        return false;
    }

    /**
     * Searches in all permited DNA matrix diagonals if its mutant or not starting from top left corner to bottom right corner
     * @param matrix1 DNA matrix
     * @param matrix2 DNA transpone matrix
     * @param matrix3 DNA antitranspose matrix
     * @return boolean true if is mutant, false if not
     */
    public boolean leftToRightDiagonal(char[][] matrix1, char[][] matrix2, char[][] matrix3) {
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

    /**
     * Searches in all permited DNA matrix diagonals if its mutant or not starting from top right corner to bottom left corner
     * @param matrix1 DNA matrix
     * @return boolean true if is mutant, false if not
     */
    public boolean rightToLeftDiagonal(char[][] matrix1) {
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

    /**
     * Searches 4 repeated characters consecutively in a char array
     * @param string DNA chain
     * @return boolean true if it foundS 4 reapeted characters consecutively, false if not
     */
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

    /**
     * traspose a matrix
     * @param DNAString DNA matrix
     * @return char[][] transposed matrix
     */
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

    /**
     * antitraspose a matrix
     * @param matrix DNA matrix
     * @return char[][] antitransposed matrix
     */
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

    /**
     * prints a matrix for debugging
     * @param matrix matrix
     */
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

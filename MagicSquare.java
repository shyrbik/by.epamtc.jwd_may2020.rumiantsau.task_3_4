package by.epamtc.rumiantsau.task_03_04;

public class MagicSquare {
    public static void main(String[] args) {

        int n = 8;
        int[][] array;
        int[][] array2 = new int[n][n];

        array = arrayFilling(n);
        //для кратных 4
        if (n % 2 == 0){
            //меняем элементы главной и побочной диоганали
            array = nMultipleFour(n, array);
        }
        //для нечетных 3, 5, 7, ..., 2n+1
        if (n % 2 != 0){
            array = nOdd(n, array2);
        }

        System.out.println("Magical Square for N = " + n);
        printArray(n, array);


    }

    private static int[][] nOdd(int n, int[][] array2) {
        //описание алгоритма:
    /*Поставим число 1 в верхнюю клетку центрального столбца. Далее будем двигаться по диагонали вправо-вверх,
    расставляя в клетки последовательно числа 234… . Если мы вышли за пределы таблицы вверх, то нужно перейти к
    нижней клетке того же столбца и продолжить с нее. Если мы вышли за правую границу, нужно перейти к левой
    клетке той строки, куда мы должны были попасть. Если же мы одновременно вышли и вверх, и вправо, то нужно
    перейти в левую нижнюю клетку квадрата.
Если в следующей клетке на нашем пути уже стоит число, то вместо хода “вправо-вверх” нужно сделать ход “вниз”
(опять же, если мы при этом выйдем за границы квадрата, нужно перейти к верхней клетке того же столбца).
*/
        int i = 0;
        int j = n/2;
        int value = 1;
        boolean notNull = false;
        do {

           while(array2[i][j] != 0){
               i++;
                if (i == n){
                    i = 0;
                }
               notNull = true;
           }
           if (notNull) {
               i++;
           j--;
               notNull = false;
           }
               array2[i][j] = value;
               i--;
               j++;
            if ((i < 0) && (j > (n-1))){
                j--;
                i = i+2;
            }
            else {
                if (i < 0) {
                    i = n-1;
                }
                if (j > (n-1)) {
                    j = 0;
                }
            }
//            System.out.println("i =" + i + " j = " + j);
//            printArray(n, array2);
            value++;

        } while (value <= n*n);

        return array2;
    }

    private static int[][] nMultipleFour(int n, int[][] array) {
        for (int i = 0, j = n-1, reverse; i < n/2; i++,j--) {
            //меняем местами элементы главной диоганали
            reverse = array[i][i];
            array[i][i] = array[j][j];
            array[j][j] = reverse;
            reverse = array[j][i];
            array[j][i] = array[i][j];
            array[i][j] = reverse;
        }
        return array;
    }

    private static void printArray(int n, int[][] array) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(array[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static int[][] arrayFilling(int n) {
        int[][] array = new int[n][n];
        int fillCount = 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                array[i][j] = fillCount;
                fillCount++;
            }
        }
        return array;
    }
}

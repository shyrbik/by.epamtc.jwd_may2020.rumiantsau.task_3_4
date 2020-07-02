package by.epamtc.rumiantsau.task_03_04;

public class Task_04 {
    public static void main(String[] args) {
         int n = 10;
         int[][] array;
         int[] arrayLine = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        //1. Сформировать квадратную матрицу порядка n по заданному образцу(n - четное):
        // 1 2 3 ... n
        // n n-1 n-2 ... 1
        array = arrayTaskFirst(n);
        arrayOutput(array);

        //2. Сформировать квадратную матрицу порядка n по заданному образцу(n - четное):
        // 1*2 0 .. 0
        // 0 0 .. n * (n+1)
        array = arrayTaskSecond(n);
        arrayOutput(array);

       // 3. Сформировать квадратную матрицу порядка n по заданному образцу(n - четное):
        //11..11
        //01..10
        //11..11
        array = arrayTaskThird(n);
        arrayOutput(array);

        //4. Дан линейный массив  . Получить действительную квадратную матрицу порядка n:
        array = arrayTaskFourth(arrayLine);
        arrayOutput(array);

        //5.mulMatrixResult
        array= mulMatrixByMatrix(arrayTaskSecond(n), arrayTaskFirst(n));
        arrayOutput(array);


    }

    private static void arrayOutput(int[][] array) {
        int n = array.length;
        for (int[] ints : array) {
            for (int j = 0; j < n; j++) {
                System.out.print(ints[j] + " ");
            }
            System.out.println();
        }
        System.out.println("Print Complete!" + "\n");
    }

    private static int[][] arrayTaskFirst(int n) {
        int number;
        int[][] array = new int[n][n];
        //Array filling

        for (int i = 0; i < n; i++) {
            if (i % 2 != 0) {
                number = n;
                for (int j = 0; j < n; j++, number--) {
                    array[i][j] = number;
                }

            }
            else {
                number = 1;
                for (int j = 0; j < n; j++, number++) {
                    array[i][j] = number;
                }
            }

        }

        return array;

    }

    private static int[][] arrayTaskSecond(int n) {
        int[][] array = new int[n][n];
        //Array filling
        for (int i = 0, j = 0; i < n; i++, j++) {
            array[i][j] = (j+1)*(j+2);
            }

        return array;
    }

    private static int[][] arrayTaskThird(int n) {
        int[][] array = new int[n][n];
        int k = n;
        int jStart = 0;
        //Array filling
        for (int i = 0; i < n/2; i++) {
            for (int j = jStart; j < k; j++) {
                array[i][j] = 1;
                array[k-1][j] = 1;
            }
            k--;
            jStart++;
        }

        return array;
    }

    private static int[][] arrayTaskFourth(int[] arrayLine) {
        int n = arrayLine.length;
        int[][] array = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
             array[i][j] = (int)Math.pow(arrayLine[j], i+1) ;
            }
        }

        return array;
    }



    public static int[][] mulMatrixByMatrix(int[][] firstMatrix, int[][] secondMatrix) {
        int colCountFirstMatrix = firstMatrix[0].length;
        int rowCountFirstMatrix = firstMatrix.length;
        int rowCountSecondMatrix = secondMatrix.length;
        int colCountSecondMatrix = secondMatrix[0].length;
        int[][] result = new int[rowCountFirstMatrix][colCountSecondMatrix];

        if(colCountFirstMatrix != rowCountSecondMatrix) return null; // Impossible multiply this matrices

        for(int i = 0; i < rowCountFirstMatrix; i++) {
            for(int j = 0; j < colCountSecondMatrix; j++) {
                for(int k = 0; k < colCountFirstMatrix; k++) {
                    result[i][j] += firstMatrix[i][k] * secondMatrix[k][j];
                }
            }
        }
        return result;
    }


    }




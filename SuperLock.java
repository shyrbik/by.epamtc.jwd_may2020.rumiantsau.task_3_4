package by.epamtc.task_03_04.rumiantsau;

import java.util.Random;
//«Суперзамок». Секретный замок для сейфа состоит из 10 расположенных в рад ячеек, в которые
// надо вставить игральные кубики.
// Но дверь открывается только в том случае, когда в любых трех соседних ячейках сумма точек на
// передних гранях кубиков равна 10.
// (Игральный кубик имеет на каждой грани от 1 до 6 точек).
// Напишите программу, которая разгадывает код замка при условии, что два кубика уже вставлены в ячейки.

public class SuperLock {
    public static void main(String[] args) {
        int[] unlockNum;
        int indexFirst;
        int indexSecond;
        Random random = new Random();

        //Вычисляем случайные индексы для вставки двух кубиков
        do {
            indexFirst = random.nextInt(9);
            indexSecond = random.nextInt(9);
        } while ((indexFirst == indexSecond) && (indexFirst > indexSecond));

        //Вычисляем случайные значения кубиков
        unlockNum = randomValuesCubes(indexFirst, indexSecond, random);

        switch (indexSecond - indexFirst){
            //кубики вставлены рядом
            case(1):
                cubesOneAfterAnother(unlockNum, indexFirst, indexSecond);
                break;
            //кубики вставлены с растоянием в один
            case(2):
                cubesThroughOne(unlockNum, indexFirst, indexSecond);
                break;
            //кубики вставлены с растоянием в два и более
            default:
                cubesTwoOrMore(unlockNum, indexFirst, indexSecond);
                break;
        }

    }

    private static int[] randomValuesCubes(int indexFirst, int indexSecond, Random random) {
        int[] unlockNum = {1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
        if ((indexSecond - indexFirst) <= 2 ) {
            do {
                unlockNum[indexFirst] = random.nextInt(5) + 1;
                unlockNum[indexSecond] = random.nextInt(5) + 1;
            } while ((unlockNum[indexFirst] + unlockNum[indexSecond]) >= 10 );
        }
        else {
            unlockNum[indexFirst] = random.nextInt(5) + 1;
            unlockNum[indexSecond] = random.nextInt(5) + 1;
        }
        System.out.println("Start combination: ");
        for (int i : unlockNum) {
            System.out.print(i + " ");
        }
        System.out.println();
        return unlockNum;
    }

    private static void cubesThroughOne(int[] unlockNum, int indexFirst, int indexSecond) {
        unlockNum[indexFirst + 1] = 10 - unlockNum[indexFirst] - unlockNum[indexSecond];
        if (indexFirst != 0){
            for (int i = indexFirst-1; i > -1 ; i--) {
                unlockNum[i] = 10 - unlockNum[i+1] - unlockNum[i+2];
            }
        if (indexSecond != 9) {
            for (int i = indexSecond+1; i < 10; i++) {
                unlockNum[i] = 10 - unlockNum[i-1] - unlockNum[i-2];
            }
        }
        }

        printUnlockCombination(unlockNum);
    }

    private static void cubesOneAfterAnother(int[] unlockNum, int indexFirst, int indexSecond) {
        if (indexFirst != 0){
            //заполняем значения от первого вставленного кубика до нулевого
            for (int i = indexFirst-1; i > -1; i--) {
                unlockNum[i] = 10 - unlockNum[i+1] - unlockNum[i+2];
            }
        }
        else {
            for (int i = 2; i < 10; i++) {
                unlockNum[i] = 10 -  unlockNum[i-1] -  unlockNum[i-2];
            }
        }

        if (indexSecond != 9){
            for (int i = indexSecond+1; i < 10; i++) {
                unlockNum[i] = 10 - unlockNum[i-1] - unlockNum[i-2];
            }
        }
        printUnlockCombination(unlockNum);
    }

    private static int[] cubesTwoOrMore(int[] unlockNum, int indexFirst, int indexSecond) {
        //Если кубики вставлены на расстоянии 2 друг от друга, то они дожны быть равны,
        // иначе задача не имеет решения, наглядно, если А и Д это вставленные кубики:
        // А + б + с = 10
        // Д + б + с = 10
        // Д = 10 - (б + с)
        // А = 10 - (б + с)
        if ((unlockNum[indexSecond] != unlockNum[indexFirst]) && ((indexSecond - indexFirst) == 3)) {
            System.out.println("Impossible to open with such numbers and order to input");
        }

        else{
            while ((unlockNum[indexFirst] + unlockNum[indexFirst +1] + unlockNum[indexFirst+2] != 10)) {
                if ((unlockNum[indexFirst+1] < 5) && ((unlockNum[indexFirst+1] + unlockNum[indexFirst]) <=9)){
                    unlockNum[indexFirst+1]++;
                }
                else {
                    if ((unlockNum[indexFirst + 2] < 5) &&
                            ((unlockNum[indexFirst + 2] + unlockNum[indexFirst]) <= 9)){
                        unlockNum[indexFirst + 2]++;
                    }
                }
            }

            for (int i = indexFirst-1; i > -1; i--) {
                if (i != indexFirst)
                unlockNum[i] = 10 - unlockNum[i+1] - unlockNum[i+2];
            }

            for (int i = indexFirst+2; i < indexSecond-1; i++) {
                unlockNum[i] = 10 - unlockNum[i-1] - unlockNum[i-2];
            }
            //unlockNum[indexSecond-1] = 10 - unlockNum[indexSecond-2] - unlockNum[indexSecond];

            for (int i = indexSecond+1; i < 10; i++) {
                if (i != indexFirst)
                unlockNum[i] = 10 - unlockNum[i-1] - unlockNum[i-2];
            }

            printUnlockCombination(unlockNum);
        }
        return unlockNum;
    }

    private static void printUnlockCombination(int[] unlockNum) {
        System.out.println("Combination to unlock: ");
        for (int i : unlockNum) {
            System.out.print(i + " ");
        }
    }

}

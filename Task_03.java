package by.epamtc.rumiantsau.task_03_04;

public class Task_03 {
    public static void main(String[] args) {
        double[] initialRealArray = new double[]{1, 2, 3, 4, -99, 9.02, -111.14, 3, 4, 8, 78, 54, 12, 14};
        int[] initialIntArray = new int[]{1, 2, 3, 4, 1, 9, -111, 3, 1, 2, 2, 54, 2, 1};

        System.out.println("minOfMostFrequencyElements = " + minOfMostFrequencyElements(initialIntArray));

        System.out.println("max(a1+a2n, a2+a2n-1, ...,an+an-1) = " + maxSumFirstLastElementArray(initialRealArray));

        System.out.println("Array Without Min Elements:");
        for (double element : arrayWithoutMinValue(initialRealArray)) {
            System.out.print(element + " ");
        }
        System.out.println();

        System.out.println("max(a2,..,a2k) + min(a1,..,a2k-1) = " +  sumMinOddMaxEvenArrayElem(initialRealArray));


    }

    private static double sumMinOddMaxEvenArrayElem(double[] initialRealArray) {
        double maxEvenElementsArray = Double.MIN_VALUE;
        double mimOddElementsArray = Double.MAX_VALUE;

        for (int i = 0; i < initialRealArray.length; i++) {
            if (i % 2 == 0) {
                if (maxEvenElementsArray < initialRealArray[i]){
                    maxEvenElementsArray = initialRealArray[i];
                }
            }
            else {
                if (mimOddElementsArray > initialRealArray[i]){
                    mimOddElementsArray = initialRealArray[i];
                }
            }
        }

        return mimOddElementsArray + maxEvenElementsArray;
    }

    private static int minOfMostFrequencyElements(int[] initialIntArray) {
        //в массиве будет зранится частотность каждого элемента
        int[] arrayElementFrequency = new int[initialIntArray.length];
        int maxCountFrequency;

        for (int i = 0; i < initialIntArray.length; i++) {
            int countFrequency = 0;
            for (int value : initialIntArray) {
                if (initialIntArray[i] == value){
                    countFrequency++;
                }
            }
            arrayElementFrequency[i] = countFrequency;
        }

        maxCountFrequency = maxArrayElement(arrayElementFrequency);

        int minOfMostFreqElements = Integer.MAX_VALUE;
        for (int i = 0; i < arrayElementFrequency.length; i++) {
            if(arrayElementFrequency[i] == maxCountFrequency)
                if(minOfMostFreqElements > initialIntArray[i]) {
                    minOfMostFreqElements = initialIntArray[i];
                }
        }

        return minOfMostFreqElements;
    }

    private static double[] arrayWithoutMinValue(double[] initialArray) {
        double minArrayElement = minArrayElement(initialArray);
        int countMinValue = 0;

        for (double v : initialArray) {
            if (v == minArrayElement){
                countMinValue++;
            }
        }

        double[] arrayWithoutMin = new double[initialArray.length-countMinValue];

        for (int i = 0, j = 0; i < initialArray.length; i++) {
            if (initialArray[i] != minArrayElement){
                arrayWithoutMin[j] = initialArray[i];
                j++;
            }
        }

        return arrayWithoutMin;
    }

    private static double minArrayElement(double[] initialArray) {
        double minArrayElement = Double.MAX_VALUE;

        for (double v : initialArray) {
            if (minArrayElement > v) {
                minArrayElement = v;
            }
        }

        return minArrayElement;
    }


    private static int maxArrayElement(int[] initialArray) {
        int maxArrayElement = Integer.MIN_VALUE;

        for (int value : initialArray) {
            if (maxArrayElement < value){
                maxArrayElement = value;
            }
        }

        return maxArrayElement;
    }

    private static double maxSumFirstLastElementArray(double[] initialArray) {
        double maxSum = Double.MIN_VALUE;
        double sum = 0;

        for (int i = 0; i < initialArray.length / 2; i++) {
            sum = initialArray[i] + initialArray[initialArray.length - 1 - i];
            if (sum > maxSum) {
                maxSum = sum;
            }
        }

        return maxSum;
    }


}

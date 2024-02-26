package classTest;

import java.util.Arrays;
import java.util.Random;

public class Chat {
    public static void main(String[] args) {
        Random random = new Random();
        int arraySize = 5;
        int[] datas = new int[arraySize];

        // 배열에 랜덤 값 할당
        for (int i = 0; i < datas.length; i++) {
            datas[i] = random.nextInt(10) + 1;
        }

        System.out.println("Generated array: " + Arrays.toString(datas));

        int key = 2;
        int keyIndex = findKeyIndex(datas, key);

        if (keyIndex != -1) {
            System.out.println("Key found at index: " + keyIndex);
        } else {
            System.out.println("Key not found in the array.");
        }
    }

    private static int findKeyIndex(int[] array, int key) {
        for (int i = 0; i < array.length; i++) {
            if (key == array[i]) {
                return i;
            }
        }
        return -1;
    }
}

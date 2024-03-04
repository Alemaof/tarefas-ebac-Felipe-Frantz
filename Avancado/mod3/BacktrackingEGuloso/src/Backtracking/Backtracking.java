package Backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Backtracking {

    public static void main(String[] args) {
        int[] coord = new int[]{1, 2, 3, 4, 5, 6, 7};
        int comb = 3;

        List<int[]> list = buscarCombinacoes(coord, comb);
        list.forEach(array -> System.out.println(Arrays.toString(array)));
        System.out.println(list.size());
    }

    private static List buscarCombinacoes(int[] coord, int comb) {
        List<int[]> list = new ArrayList<>();
        int[] array = new int[comb];

        fazerCombinacoes(list, coord, array, comb, 0, 0);

        return list;
    }

    private static boolean fazerCombinacoes(List<int[]> list, int[] coord, int[] array, int comb, int indexArray, int indexCoord) {

        if (indexArray==array.length) {
            int[] clone = array.clone();
            for(int[] arr : list) {
                if(Arrays.equals(arr,array)){
                    return true;
                }
            }
            list.add(clone);
            return true;
        }

        for (int j = indexArray; j <= array.length - comb + indexArray; j++) {
            array[j] = coord[indexCoord];
            fazerCombinacoes(list, coord, array, comb, j+1, indexCoord+1);
        }

        for (int i = indexCoord; i <= coord.length - comb + indexArray; i++) {
            array[indexArray] = coord[i];
            fazerCombinacoes(list, coord, array, comb, indexArray+1, i+1);
        }

        return true;
    }
}

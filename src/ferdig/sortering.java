package ferdig;

public class sortering {
    private static void quicksort(int[] a, int fra, int til) {
        if (fra >= til) return;

        int mid = fra + (til - fra) / 2;
        ekstra.bytt(a, mid, til);

        int pivot = a[til];
        int v = fra;
        int h = til - 1;

        while (true) {
            while (v <= h && a[v] < pivot) v++;
            while (v <= h && a[h] >= pivot) h--;

            if (v < h) {
                ekstra.bytt(a, v, h);
            }
            else break;
        }

        ekstra.bytt(a,v, til);
        quicksort(a, fra, v - 1);
        quicksort(a, v + 1, til);
    }
    public static void quickSort(int [] array){
        quicksort(array,0,array.length-1);
    }


    public static void mergeSort(int[] array) {
        if (array.length < 2) {
            return;
        }

        int mid = array.length / 2;
        int[] left = new int[mid];
        int[] right = new int[array.length - mid];

        for (int i = 0; i < mid; i++) {
            left[i] = array[i];
        }
        for (int i = mid; i < array.length; i++) {
            right[i - mid] = array[i];
        }

        mergeSort(left);
        mergeSort(right);
        merge(array, left, right);
    }

    private static void merge(int[] array, int[] left, int[] right) {
        int i = 0, j = 0, k = 0;

        while (i < left.length && j < right.length) {
            if (left[i] <= right[j]) {
                array[k] = left[i];
                k++;i++;
            } else {
                array[k] = right[j];
                k++;j++;
            }
        }

        while (i < left.length) {
            array[k] = left[i];
            k++;i++;
        }

        while (j < right.length) {
            array[k] = right[j];
            k++;j++;
        }
    }
    public static void bubblesort(int[] array) {
        if (array.length < 2) return;

        for (int i = array.length-1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (array[j] > array[j+1]) {
                    ekstra.bytt(array, j, j+1);
                }
            }
        }



    }


}



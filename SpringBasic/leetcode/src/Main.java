
public class Main {
    public static void main(String[] args) {

        int[] arr = {3, -1, 4, -5, 2, -7};
        int finalTrocas = 0;
        System.out.println("\nDESORDENADOS");
        for (int num : arr) {
            System.out.print(num + " ");
        }

        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length -i -1; j++) {
                if(arr[j] < arr[j + 1]){
                    int temp = arr[j];

                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    finalTrocas++;


                }
            }
        }

        System.out.println("\nORDENADOS");
        for (int num : arr) {
            System.out.print(num + " ");
        }

        System.out.println("\nO numero final de trocas foi: " + finalTrocas);

    }
}
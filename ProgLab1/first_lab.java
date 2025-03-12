public class first_lab {
    public static void main(String[] args) {
        int[][] r = {{1,2}, {3,4}};
        System.out.print(r);
        int size_с = 8;
        int[] с = new int[size_с];

        int num = 6;
        int count = 0;
        while(count < size_с){
            if (num % 2 == 0) {
                с[count] = num;     
            }
            num += 2;
            count++;
        }

        int size_x = 13;
        double[] x = new double[size_x];

        double round_helper_1 = 10;
        for (int i = 0; i < size_x; i++){
            x[i] = Math.round(Math.random() * 22 - 13 * round_helper_1) / round_helper_1;
            // x[i] = Math.round(x[i] * round_helper_1) / round_helper_1;
        }

        int vert_two_dim_int_array = 8;
        int hor_two_dim_int_array = 13;
        double[][] two_dim_double_array = new double[vert_two_dim_int_array][hor_two_dim_int_array];
        for (int i = 0; i < size_с; i++){
            for (int j = 0; j < size_x; j++){
                if (с[i] == 16){
                    two_dim_double_array[i][j] = Math.sin(Math.pow(Math.E, Math.pow(x[j]*(x[j] - 1), 2)));
                }
                else if ((с[i] == 6) || (с[i] == 12) || (с[i] == 14) || (с[i] == 20)){
                    two_dim_double_array[i][j] = Math.log(Math.pow(Math.cos(Math.pow((Math.pow(x[j] * (x[j] + 1), x[j]) + 3) / 2, Math.cos(x[j]))), 2));
                }
                else {
                    two_dim_double_array[i][j] = Math.pow((Math.cos(Math.pow((2 + Math.pow(Math.E, x[j])), 2)) + 0.25) / Math.cbrt(Math.sin(Math.pow(x[j], x[j]))), 2);
                }
            }
        }

        for (int i = 0; i < size_с; i++){
            for (int j = 0; j < size_x; j++){
                System.out.printf("%-11.4f", two_dim_double_array[i][j]);;
            }
            System.out.print("\n");
        }    
    }
}
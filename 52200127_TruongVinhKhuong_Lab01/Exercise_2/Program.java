

import vn.edu.tdtu.*;

public class Program {
    public static void main(String[] args) {
        int a[] = {1, 6, 3, 2, 8};
        int b[] = {9, 11, 5, 7};
        int c[] = ArrayHandler.merge(a, b);
        ArrayOutput.print(c);
        ArrayHandler.sort(c);
        ArrayOutput.write(c, "output.txt");

        
    }
}

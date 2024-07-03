import java.util.*;

class Challenge_1 {


    public static List<List<Integer>> findSubsets(int[] A, int sum) {
        List<List<Integer>> result = new ArrayList<>();
        findSubsetsRecursive(A, sum, 0, new ArrayList<>(), result);
        return result;
    }

    private static void findSubsetsRecursive(int[] A, int sum, int index, List<Integer> current, List<List<Integer>> result) {
        if (sum == 0) {
            result.add(new ArrayList<>(current));
            return;
        }
        if (sum < 0 || index == A.length) {
            return;
        }

        current.add(A[index]);
        findSubsetsRecursive(A, sum - A[index], index, current, result);

        current.remove(current.size() - 1);
        findSubsetsRecursive(A, sum, index + 1, current, result);
    }

    public static void main(String[] args) {
        try (Scanner s = new Scanner(System.in)) {
            List<Integer> tempArray = new ArrayList<>();
            System.out.println("Enter elements (enter x to finish):");
            while (s.hasNextInt()) {
                tempArray.add(s.nextInt());
            }
            s.next();
            int[] arr = tempArray.stream().mapToInt(i -> i).toArray();

            System.out.println("Enter target sum:");
            while (!s.hasNextInt()) {
                System.out.println("Please enter a valid integer:");
                s.next(); 
            }
            int sum = s.nextInt();

            List<List<Integer>> subsets = findSubsets(arr, sum);
            System.out.println("A solution set is:");
            System.out.println(subsets);
        }
    }
}

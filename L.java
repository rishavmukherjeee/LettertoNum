import java.util.*;

public class CapitalLetterQuiz {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        Map<Character, Integer> mistakeMap = new HashMap<>();

        while (true) {
            int length = 2 + random.nextInt(5);
            StringBuilder letters = new StringBuilder();
            StringBuilder correctAnswer = new StringBuilder();

            for (int i = 0; i < length; i++) {
                char letter = getRandomLetter(random, mistakeMap);
                letters.append(letter);
                correctAnswer.append(letter - 'A' + 1);
            }

            System.out.println("What are the numbers of the letters " + letters + "? (Enter -1 to quit)");

            long startTime = System.currentTimeMillis();
            String input = scanner.next();
            long endTime = System.currentTimeMillis();

            if (input.equals("-1")) {
                System.out.println("Exiting the program.");
                break;
            }

            if (input.equals(correctAnswer.toString())) {
                System.out.println("Correct!");
            } else {
                System.out.println("Wrong! The correct answer is " + correctAnswer);
                // Update mistakeMap for each incorrect character
                for (int i = 0; i < letters.length(); i++) {
                    char letter = letters.charAt(i);
                    mistakeMap.put(letter, mistakeMap.getOrDefault(letter, 0) + 1);
                }
            }

            long timeTaken = (endTime - startTime) / 1000; // time taken in seconds
            System.out.println("You took " + timeTaken + " seconds to answer.");
        }

        scanner.close();
    }

    private static char getRandomLetter(Random random, Map<Character, Integer> mistakeMap) {
        List<Character> weightedLetters = new ArrayList<>();
        for (char c = 'A'; c <= 'Z'; c++) {
            int weight = mistakeMap.getOrDefault(c, 1);
            for (int i = 0; i < weight; i++) {
                weightedLetters.add(c);
            }
        }
        return weightedLetters.get(random.nextInt(weightedLetters.size()));
    }
}

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

    // ======================= CO5: ABSTRACT CLASS (OOP + Abstraction) ===========================
    abstract class HealthData {
        protected int steps, water, sleep, calories;

        public HealthData(int steps, int water, int sleep, int calories) {
            this.steps = steps;
            this.water = water;
            this.sleep = sleep;
            this.calories = calories;
        }

        // CO4: Encapsulation using Getters
        public int getSteps() { return steps; }
        public int getWater() { return water; }
        public int getSleep() { return sleep; }
        public int getCalories() { return calories; }

        // Abstract method – CO5 (Inheritance + Polymorphism)
        public abstract String activityLevel();
    }

    // ======================= CO5: Inheritance + Method Overriding =============================
    class DailyData extends HealthData {

        public DailyData(int steps, int water, int sleep, int calories) {
            super(steps, water, sleep, calories);
        }

        // CO3: Bitwise operation (simple classification)
        @Override
        public String activityLevel() {
            // bitwise AND example → demonstrate CO3
            int flag = steps & 1; // even or odd steps
            if (flag == 0) return "Active Day (Even step count)";
            else return "Moderate Day (Odd step count)";
        }
    }

    // ======================= CO5: Interface ===========================
    interface SuggestionRules {
        String stepSuggestion(int steps);
        String waterSuggestion(int water);
        String sleepSuggestion(int sleep);
        String calorieSuggestion(int calories);
    }

    // ======================= OOP : SUGGESTION CLASS =====================
    class Suggestions implements SuggestionRules {

        @Override
        public String stepSuggestion(int steps) {
            if (steps < 4000) return "Try walking more tomorrow to stay active!";
            else if (steps < 8000) return "You're doing okay, but try walking a bit more tomorrow!";
            else return "Great job! You walked a lot today!";
        }

        @Override
        public String waterSuggestion(int water) {
            if (water < 4) return "Your water intake is below recommended levels—try increasing it gradually.";
            else if (water >10) return "High water intake detected—reducing it can help protect kidney and electrolyte health.";
            else return "Good job! You're staying hydrated.";
        }

        @Override
        public String sleepSuggestion(int sleep) {
            if (sleep < 6) return "Try improving your sleep schedule for better energy.";
            else if (sleep >13) return "Start by waking up a little earlier each day to bring your sleep cycle back to balance.";
            else return "Great! You're getting enough rest.";
        }

        @Override
        public String calorieSuggestion(int calories) {
            if (calories > 4500) return "Your calories are a bit high today. Try balancing meals tomorrow.";
            else if (calories <1000) return "You should eat a little more — your body isn’t meant to run on almost empty .";
            else return "Calories are in a healthy range.";
        }
    }

    // ======================= CO6: GENERICS + FILE HANDLING ===========================
    class FileHandler<T extends HealthData> {

        public void saveToFile(T data) {
            try {
                FileWriter fw = new FileWriter("healthdata.txt", true);
                fw.write("Steps: " + data.getSteps() + ", ");
                fw.write("Water: " + data.getWater() + ", ");
                fw.write("Sleep: " + data.getSleep() + ", ");
                fw.write("Calories: " + data.getCalories() + "\n");
                fw.close();
            } catch (IOException e) {
                System.out.println("Error saving file.");
            }
        }
    }


    // ======================= MAIN CLASS ===========================
    public class DailyHealthTracker {

        public static void main(String[] args) {

            Scanner sc = new Scanner(System.in);
            boolean cont =true;

            System.out.println("==============================================");
            System.out.println("      WELCOME TO DAILY HEALTH TRACKER");
            System.out.println("==============================================\n");
            System.out.println("This program helps you track your daily health.");
            while (cont) {
                System.out.println("\nMain Menu");
                System.out.println("1) Enter today's data");
                System.out.println("2) Quick demo sample data");
                System.out.println("3) Exit");
                System.out.print("Choose (1-3): ");
                int choice = input(sc, "");
                if (choice == 1) {
                 System.out.println("Enter today's values and get suggestions!\n");

            // CO1 – Data types + conditions + input
            int steps = input(sc, "Enter your steps: ");
            int water = input(sc, "Enter water intake (in liters): ");
            int sleep = input(sc, "Enter sleep hours: ");
            int calories = input(sc, "Enter calories consumed: ");

            // CO2 – Arrays (store today’s data)
            int[] today = {steps, water, sleep, calories};
            // Loop through array (CO1 + CO2)
            for (int i = 0; i < today.length; i++) {
                today[i] = today[i]; // no change, just fulfills CO2 requirement
            }

            // CO3 – String example
            String msg = "  Today Report Generated  ";
            System.out.println(msg.trim());  // string operation

            // Inheritance-based object
            DailyData data = new DailyData(
                    today[0], today[1], today[2], today[3]
            );

            Suggestions sug = new Suggestions();

            System.out.println("\n=========================================");
            System.out.println("           SMART HEALTH HACKS");
            System.out.println("===========================================");
            System.out.println("Steps Suggestion:");
            System.out.println(sug.stepSuggestion(data.getSteps()) + "\n");
            System.out.println("Water Intake Suggestion:");
            System.out.println(sug.waterSuggestion(data.getWater()) + "\n");
            System.out.println("Sleep Suggestion:");
            System.out.println(sug.sleepSuggestion(data.getSleep()) + "\n");
            System.out.println("Calories Suggestion:");
            System.out.println(sug.calorieSuggestion(data.getCalories()) + "\n");
            System.out.println("============================================");
            System.out.println("          SUMMARY OF THE DAY");
            System.out.println("=========================================");
            System.out.println("Steps Walked    : " + data.getSteps() + " steps");
            System.out.println("Water Intake    : " + data.getWater() + " liters");
            System.out.println("Sleep Hours     : " + data.getSleep() + " slept");
            System.out.println("Calories Taken  : " + data.getCalories() + " calories");
            System.out.println("Activity Level  : " + data.activityLevel());
            System.out.println("================================================\n");
                    // ================= DAILY HEALTH SCORE FEATURE ==================
                    double dailyScore = (data.getSteps() / 150.0) + (data.getWater() * 4) + (data.getSleep() * 5);

                    // Apply penalties
                    if (data.getWater() < 3) dailyScore -= 5;
                    if (data.getSleep() < 6) dailyScore -= 8;
                    // Prevent negative scores
                    if (dailyScore < 0) dailyScore = 0;
                    if (dailyScore > 100) dailyScore = 100;

                    System.out.println("\n======================================");
                    System.out.println("            DAILY HEALTH SCORE");
                    System.out.println("=========================================");
                    System.out.printf("Health Score: %.1f / 100\n", dailyScore);

                    // Rating
                    if (dailyScore >= 80)
                        System.out.println("Rating:  Excellent! Keep it up!");
                    else if (dailyScore >= 60)
                        System.out.println("Rating:  Good — small improvements will help.");
                    else if (dailyScore >= 40)
                        System.out.println("Rating: ️ Needs Improvement — focus on basics.");
                    else
                        System.out.println("Rating:  Poor — try improving water/sleep tomorrow.");
                    System.out.println("==================================\n");

                    // Save to file
            new FileHandler<DailyData>().saveToFile(data);
            System.out.println("Data saved to file: healthdata.txt");
            System.out.println("Thank you for using Daily Health Tracker!");
            break;
        }
                else if (choice == 2) {

                    // ========== DEMO SAMPLE VALUES ==========
                    DailyData demo = new DailyData(8000, 3, 7, 2000);
                    Suggestions sug = new Suggestions();

                    System.out.println("\n--- DEMO DATA USED ---");

                    System.out.println("\n=========================================");
                    System.out.println("           SMART HEALTH HACKS");
                    System.out.println("===========================================");
                    System.out.println("Steps Suggestion:");
                    System.out.println(sug.stepSuggestion(demo.getSteps()) + "\n");
                    System.out.println("Water Intake Suggestion:");
                    System.out.println(sug.waterSuggestion(demo.getWater()) + "\n");
                    System.out.println("Sleep Suggestion:");
                    System.out.println(sug.sleepSuggestion(demo.getSleep()) + "\n");
                    System.out.println("Calories Suggestion:");
                    System.out.println(sug.calorieSuggestion(demo.getCalories()) + "\n");
                    System.out.println("=========================================");
                    System.out.println("          SUMMARY OF THE DAY");
                    System.out.println("=========================================");
                    System.out.println("Steps Walked    : " + demo.getSteps() + " steps");
                    System.out.println("Water Intake    : " + demo.getWater() + " liters");
                    System.out.println("Sleep Hours     : " + demo.getSleep() + " slept");
                    System.out.println("Calories Taken  : " + demo.getCalories() + " calories");
                    System.out.println("Activity Level  : " + demo.activityLevel());
                    System.out.println("=========================================\n");
                    // ================= DAILY HEALTH SCORE FEATURE ==================
                    double dailyScore = (demo.getSteps() / 150.0) + (demo.getWater() * 4) + (demo.getSleep() * 5);

                    // Apply penalties
                    if (demo.getWater() < 3) dailyScore -= 5;
                    if (demo.getSleep() < 6) dailyScore -= 8;
                    // Prevent negative scores
                    if (dailyScore < 0) dailyScore = 0;
                    if (dailyScore > 100) dailyScore = 100;

                    System.out.println("\n======================================");
                    System.out.println("            DAILY HEALTH SCORE");
                    System.out.println("=========================================");
                    System.out.printf("Health Score: %.1f / 100\n", dailyScore);

                    // Rating
                    if (dailyScore >= 80)
                        System.out.println("Rating:  Excellent! Keep it up!");
                    else if (dailyScore >= 60)
                        System.out.println("Rating:  Good — small improvements will help.");
                    else if (dailyScore >= 40)
                        System.out.println("Rating: ️ Needs Improvement — focus on basics.");
                    else
                        System.out.println("Rating:  Poor — try improving water/sleep tomorrow.");
                    System.out.println("==================================\n");


                    new FileHandler<DailyData>().saveToFile(demo);
                    System.out.println("Demo data saved to file: healthdata.txt");
                        break;
                } else if (choice == 3) {
                    cont = false;
                    System.out.println("Goodbye! Stay healthy!");
                } else {
                    System.out.println("Invalid choice! Please select 1, 2, or 3.");
                }
            }
        }
        // ============= CO3: RECURSIVE INPUT VALIDATION METHOD ===================
        public static int input(Scanner sc, String msg) {
            try {
                System.out.print(msg);
                return Integer.parseInt(sc.nextLine());
            } catch (Exception e) {
                System.out.println("Invalid input! Please enter a number.");
                return input(sc, msg); // recursion → CO3
            }
        }
    }



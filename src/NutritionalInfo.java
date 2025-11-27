public class NutritionalInfo {
    private String itemName;
    private int calories;
    private double weight;  // in grams
    private double protein;  // in grams
    private double carbs;    // in grams
    private double fat;      // in grams
    private double sugar;    // in grams
    private double sodium;   // in milligrams
    private double fiber;    // in grams

    public NutritionalInfo(String itemName, int calories, double weight,
                           double protein, double carbs, double fat,
                           double sugar, double sodium, double fiber) {
        this.itemName = itemName;
        this.calories = calories;
        this.weight = weight;
        this.protein = protein;
        this.carbs = carbs;
        this.fat = fat;
        this.sugar = sugar;
        this.sodium = sodium;
        this.fiber = fiber;
    }

    // Getters
    public String getItemName() { return itemName; }
    public int getCalories() { return calories; }
    public double getWeight() { return weight; }
    public double getProtein() { return protein; }
    public double getCarbs() { return carbs; }
    public double getFat() { return fat; }
    public double getSugar() { return sugar; }
    public double getSodium() { return sodium; }
    public double getFiber() { return fiber; }

    // Format for display
    public String getFormattedInfo() {
        StringBuilder info = new StringBuilder();
        info.append("═══════════════════════════════════\n");
        info.append("   NUTRITIONAL FACTS\n");
        info.append("═══════════════════════════════════\n\n");
        info.append(String.format("Item: %s\n\n", itemName));
        info.append(String.format("Serving Size: %.0fg\n\n", weight));
        info.append("───────────────────────────────────\n");
        info.append(String.format("Calories:        %d cal\n\n", calories));
        info.append("───────────────────────────────────\n");
        info.append(String.format("Total Fat:       %.1fg\n", fat));
        info.append(String.format("Carbohydrates:   %.1fg\n", carbs));
        info.append(String.format("  Sugar:         %.1fg\n", sugar));
        info.append(String.format("  Fiber:         %.1fg\n", fiber));
        info.append(String.format("Protein:         %.1fg\n", protein));
        info.append(String.format("Sodium:          %.0fmg\n", sodium));
        info.append("═══════════════════════════════════\n");

        return info.toString();
    }
}
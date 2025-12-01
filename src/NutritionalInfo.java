
public record NutritionalInfo(String itemName, int calories, double weight, double protein, double carbs, double fat,
                              double sugar, double sodium, double fiber) {
    // Format for display
    public String getFormattedInfo() {

        return "═══════════════════════════════════\n" +
                "   NUTRITIONAL FACTS\n" +
                "═══════════════════════════════════\n\n" +
                String.format("Item: %s\n\n", itemName) +
                String.format("Serving Size: %.0fg\n\n", weight) +
                "───────────────────────────────────\n" +
                String.format("Calories:        %d cal\n\n", calories) +
                "───────────────────────────────────\n" +
                String.format("Total Fat:       %.1fg\n", fat) +
                String.format("Carbohydrates:   %.1fg\n", carbs) +
                String.format("  Sugar:         %.1fg\n", sugar) +
                String.format("  Fiber:         %.1fg\n", fiber) +
                String.format("Protein:         %.1fg\n", protein) +
                String.format("Sodium:          %.0fmg\n", sodium) +
                "═══════════════════════════════════\n";
    }
}
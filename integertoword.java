class Solution {
    // Maps for numbers below 20 and for multiples of 10
    private final String[] belowTwenty = {
        "", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine",
        "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen",
        "Sixteen", "Seventeen", "Eighteen", "Nineteen"
    };

    private final String[] tens = {
        "", "", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"
    };

    // Postfixes for powers of 1000
    private final String[] thousands = {"", "Thousand", "Million", "Billion"};

    public String numberToWords(int num) {
        // Edge case: zero
        if (num == 0) return "Zero";

        StringBuilder result = new StringBuilder();

        // Index for thousands[], starts from lowest group
        int i = 0;

        // Process in groups of 3 digits (thousands, millions, billions)
        while (num > 0) {
            // Take last 3 digits
            int chunk = num % 1000;
            if (chunk != 0) {
                // Convert that 3-digit group into words + postfix
                String chunkStr = helper(chunk);
                result.insert(0, chunkStr + thousands[i] + " ");
            }
            num /= 1000;  // Move to next group
            i++;
        }

        // Trim and return
        return result.toString().trim();
    }

    // Convert a number <= 999 into English words
    private String helper(int num) {
        StringBuilder sb = new StringBuilder();
        if (num == 0) return "";
        else if (num < 20) { // Numbers below 20
            sb.append(belowTwenty[num]).append(" ");
        } else if (num < 100) { // Numbers from 20 to 99
            sb.append(tens[num / 10]).append(" ");
            sb.append(helper(num % 10)); // Process the ones digit
        } else { // Numbers from 100 to 999
            sb.append(belowTwenty[num / 100]).append(" Hundred ");
            sb.append(helper(num % 100)); // Process the last two digits
        }
        return sb.toString();
    }
}

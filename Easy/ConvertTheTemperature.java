class ConvertTheTemperature {
    public double[] bruteForceConvertTemperature(double celsius) {
        double kelvin = celsius + 273.15;
        double fahrenheit = celsius * 1.80 + 32.00;

        double[] answer = new double[2];
        answer[0] = kelvin;
        answer[1] = fahrenheit;
        return answer;
    }

    public double[] convertTemperature(double celsius) {
        return new double[] {celsius + 273.15, celsius * 1.80 + 32.00};
    }

    private static void check(String name, double[] actual, double[] expected) {
        if (actual.length != expected.length) {
            throw new AssertionError(name + " length expected " + expected.length + " but got " + actual.length);
        }

        for (int i = 0; i < actual.length; i++) {
            if (Math.abs(actual[i] - expected[i]) > 0.00001) {
                throw new AssertionError(name + " index " + i + " expected " + expected[i] + " but got " + actual[i]);
            }
        }
    }

    public static void main(String[] args) {
        ConvertTheTemperature solution = new ConvertTheTemperature();

        check("brute force sample", solution.bruteForceConvertTemperature(36.50), new double[] {309.65, 97.70});
        check("brute force zero", solution.bruteForceConvertTemperature(0.00), new double[] {273.15, 32.00});

        check("sample", solution.convertTemperature(36.50), new double[] {309.65, 97.70});
        check("zero", solution.convertTemperature(0.00), new double[] {273.15, 32.00});
        check("boiling point", solution.convertTemperature(100.00), new double[] {373.15, 212.00});
    }
}

/*
 * Brute Force:
 * I calculate Kelvin and Fahrenheit separately, then place each value into the
 * answer array.
 *
 * Time Complexity: O(1), because the conversion uses a fixed number of
 * arithmetic operations.
 * Space Complexity: O(1), because only the required answer array is stored.
 *
 * Optimal Interview Solution:
 * I return the two converted values directly from the formulas.
 *
 * Time Complexity: O(1), because the work does not grow with the input.
 * Space Complexity: O(1), because the method only returns the required result.
 */

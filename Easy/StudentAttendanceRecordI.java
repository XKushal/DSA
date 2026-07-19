class StudentAttendanceRecordI {
    public boolean bruteForceCheckRecord(String s) {
        int absentCount = 0;

        for (int index = 0; index < s.length(); index++) {
            if (s.charAt(index) == 'A') {
                absentCount++;
            }
        }

        if (absentCount >= 2) {
            return false;
        }

        for (int index = 0; index + 2 < s.length(); index++) {
            if (s.charAt(index) == 'L'
                    && s.charAt(index + 1) == 'L'
                    && s.charAt(index + 2) == 'L') {
                return false;
            }
        }

        return true;
    }

    public boolean checkRecord(String s) {
        int absentCount = 0;
        int consecutiveLateCount = 0;

        for (int index = 0; index < s.length(); index++) {
            char status = s.charAt(index);

            if (status == 'A') {
                absentCount++;
            }

            if (status == 'L') {
                consecutiveLateCount++;
            } else {
                consecutiveLateCount = 0;
            }

            if (absentCount >= 2 || consecutiveLateCount >= 3) {
                return false;
            }
        }

        return true;
    }

    private static void check(String name, boolean actual, boolean expected) {
        if (actual != expected) {
            throw new AssertionError(name + " expected " + expected + " but got " + actual);
        }
    }

    public static void main(String[] args) {
        StudentAttendanceRecordI solution = new StudentAttendanceRecordI();

        check("brute force accepts eligible record", solution.bruteForceCheckRecord("PPALLP"), true);
        check("brute force rejects late streak", solution.bruteForceCheckRecord("PPALLL"), false);

        check("accepts eligible record", solution.checkRecord("PPALLP"), true);
        check("rejects two absences", solution.checkRecord("PPAAP"), false);
        check("rejects three consecutive lates", solution.checkRecord("LLL"), false);
        check("accepts separated lates", solution.checkRecord("LPLPL"), true);
        check("accepts one absence and two lates", solution.checkRecord("ALLP"), true);
        check("rejects late streak after absence", solution.checkRecord("ALLLP"), false);
        check("accepts empty record", solution.checkRecord(""), true);
    }
}

/*
 * Brute Force:
 * I count all absences first, then make a separate pass over every length-three
 * window to look for three consecutive late marks.
 *
 * Time Complexity: O(n), where n is the length of s. The two scans are each
 * linear.
 * Space Complexity: O(1), because only counters are stored.
 *
 * Optimal Interview Solution:
 * I make one pass while tracking total absences and the current late streak,
 * returning early as soon as either attendance rule is broken.
 *
 * Time Complexity: O(n), where n is the length of s.
 * Space Complexity: O(1), because only the absence and late counters are stored.
 */

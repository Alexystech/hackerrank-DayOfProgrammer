package com.alexier;

import java.time.LocalDate;
import java.time.Year;
import java.time.temporal.ChronoField;
import java.time.temporal.ValueRange;
import java.time.YearMonth;

public class Main {
	public static void main(String... args) {
		int year = 1918;
		Result result = new Result();
		String dayOfProgrammer = result.dayOfProgrammer(year);
		System.out.println(dayOfProgrammer);
	}
}

class Result {
	public static String dayOfProgrammer(int year) {
		int days = 0;
		if (isGregorian(year)) {
			System.out.println("is Gregorian");
			days = 256 - summatoryOfDays(year, 1);
		} else {
			System.out.println("is Julian");
			days = 256 - summatoryOfDays(year, 2);
		}

		if (year == 1918) {
			days += 13;
		}
		
		return days + ".09." + year;
	}

	public static boolean isGregorian(int year) {
		return year >= 1919;
	}

	public static int summatoryOfDays(int year, int calendarSystem) {
		int summatory = 0;

		switch (calendarSystem) {
			case 1: // Gregorian
				for (int gregorianMonth = 1; gregorianMonth <= 8; gregorianMonth++) {
					summatory += calculateDaysPerMonth(year, gregorianMonth, 1);
				}
				break;
			case 2: // Julian
				for (int julianMonth = 1; julianMonth <= 8; julianMonth++) {
					summatory += calculateDaysPerMonth(year, julianMonth, 2);
				}
				break;
			default:
				break;
		}

		return summatory;
	}

	public static int calculateDaysPerMonth(int year, int month, int calendarSystem) {
		int days = 0;
		switch (calendarSystem) {
			case 1: // Gregorian
				YearMonth yearMonth = YearMonth.of(year, month);
				days = yearMonth.lengthOfMonth();
				break;
			case 2: // Julian
				days = getDaysInMonth(year, month);
				break;
			default:
				break;
		}
		return days;
	}

	public static int getDaysInMonth(int year, int month) {
        if (month == 2) {
            if (isJulianLeapYear(year)) {
                return 29;
            } else {
                return 28;
            }
        } else if (month == 4 || month == 6 || month == 9 || month == 11) {
            return 30;
        } else {
            return 31;
        }
    }

    public static boolean isJulianLeapYear(int year) {
        return year % 4 == 0;
    }
}

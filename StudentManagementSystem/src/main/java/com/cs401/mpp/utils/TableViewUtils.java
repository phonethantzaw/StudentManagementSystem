package com.cs401.mpp.utils;

import java.util.List;

public class TableViewUtils {

    public static void printTable(List<String> headers, List<List<String>> data) {
        int[] columnWidths = getColumnWidths(headers, data);
        printHeader(headers, columnWidths);
        printData(data, columnWidths);
    }

    private static int[] getColumnWidths(List<String> headers, List<List<String>> data) {
        int[] columnWidths = new int[headers.size()];

        // Find maximum width for each column
        for (int i = 0; i < headers.size(); i++) {
            int maxColumnWidth = headers.get(i).length();
            for (List<String> row : data) {
                maxColumnWidth = Math.max(maxColumnWidth, row.get(i).length());
            }
            columnWidths[i] = maxColumnWidth;
        }
        return columnWidths;
    }

    private static void printHeader(List<String> headers, int[] columnWidths) {
        printLine(columnWidths);
        for (int i = 0; i < headers.size(); i++) {
            System.out.printf("| %-" + (columnWidths[i] + 5) + "s ", headers.get(i)); // Add extra spaces for padding
        }
        System.out.println("|");
        printLine(columnWidths);
    }

    private static void printData(List<List<String>> data, int[] columnWidths) {
        for (List<String> row : data) {
            for (int i = 0; i < row.size(); i++) {
                System.out.printf("| %-" + (columnWidths[i] + 5) + "s ", row.get(i)); // Add extra spaces for padding
            }
            System.out.println("|");
        }
        printLine(columnWidths);
    }

    private static void printLine(int[] columnWidths) {
        System.out.print("+");
        for (int width : columnWidths) {
            for (int i = 0; i < width + 7; i++) { // Add extra 7 characters to account for padding and '|'
                System.out.print("-");
            }
            System.out.print("+");
        }
        System.out.println();
    }


}

package com.company;
import com.sun.xml.internal.ws.policy.PolicyMapUtil;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import java.io.*;
import java.util.*;

public class Main {

    private static boolean tryParseInt(String value) {
        try {
            Integer.parseInt(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    void bubbleSort(int arr[])
    {
        int n = arr.length;
        for (int i = 0; i < n-1; i++)
            for (int j = 0; j < n-i-1; j++)
                if (arr[j] > arr[j+1])
                {
                    // swap temp and arr[i]
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
    }

    public static void main(String[] args) throws IOException {
        Scanner scan = new Scanner(new File("scores.txt"));

        List<School> Schools = new ArrayList<>();

        double skippedSchools = 0;

        scan.nextLine();
        while (scan.hasNextLine()) {
            String SATScore = scan.nextLine();

            String[] temp = SATScore.split("\\t");

            if (temp.length > 19) {

                if (tryParseInt(temp[18]) && tryParseInt(temp[19]) && tryParseInt(temp[20]))
                {
                    Schools.add(new School(temp[1], Integer.parseInt(temp[18]) + Integer.parseInt(temp[19]) + Integer.parseInt(temp[20])));
                }
            }
        }

        System.out.println("Average SAT Scores:");
        for (int i = 0; i < Schools.size(); i++)
        {
            System.out.println(Schools.get(i).schoolName + ": " + Schools.get(i).averageScore);
        }

        int top1 = 0, top2 = 0, top3 = 0;
        int top1index = 0, top2index = 0, top3index = 0;
        for (int i = 0; i < Schools.size(); i++)
        {
            int a = Schools.get(i).averageScore;
            if (a > top1)
            {
                top3 = top2;
                top2 = top1;
                top1 = a;
                top1index = i;
            }
            else if (a > top2)
            {
                top3 = top2;
                top2 = a;
                top2index = i;
            }
            else if (a > top3)
            {
                top3 = a;
                top3index = i;
            }
        }

        System.out.println(); // Spacing this text from the last to mak it looks nicer
        System.out.println("Top three schools:");
        System.out.println("#1 - " + Schools.get(top1index).schoolName + ": " + Schools.get(top1index).averageScore);
        System.out.println("#2 - " + Schools.get(top2index).schoolName + ": " + Schools.get(top2index).averageScore);
        System.out.println("#3 - " + Schools.get(top3index).schoolName + ": " + Schools.get(top3index).averageScore);

        int total = 0;
        for (int i = 0; i < Schools.size(); i++)
        {
            total += Schools.get(i).averageScore;
        }
        System.out.println();
        System.out.println("State average: " + total / (Schools.size() - skippedSchools));

    }
}
package com.aoc.day13;

import com.aoc.day13.Packet.PacketList;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day13Part1 {

    private static final String test1 = "day13/day13-sample.txt";
    private static final String test2 = "day13/day13-a.txt";

    public static void main(String[] args) throws URISyntaxException, FileNotFoundException {

        File input = new File(Day13Part1.class.getClassLoader().getResource(test2).toURI());
        try (Scanner scanner = new Scanner(input)) {
            int currPair = 1;
            int totalSum = 0;
            Packet packet1 = null;
            Packet packet2 = null;

            int packetRead = 0;
            while (scanner.hasNext()) {
                String nextLine = scanner.nextLine();
                if (nextLine.isBlank()) {
                    continue;
                }

                if (packetRead % 2 == 0) {
                    packet1 = parsePacket(nextLine);
                    packetRead++;
                } else if (packetRead % 2 == 1){
                    packet2 = parsePacket(nextLine);
                    packetRead++;
                }

                if (packetRead % 2 == 0) {
                    System.out.println("==  PAIR: " + currPair + " ==");
                    boolean rightOrder = isRightOrder(packet1, packet2);
                    totalSum += rightOrder ? currPair : 0;
                    System.out.println("    - Inputs are " + (rightOrder ? "**" : "NOT ") + "in the right order" + (rightOrder ? "**" : ""));
                    packet1 = null;
                    packet2 = null;
                    currPair++;
                    System.out.println("");
                }
            }

            System.out.println(totalSum);
        }
    }

    private static Packet parsePacket(String nextLine) {
        System.out.println("Parsing: " + nextLine);
        try (Scanner scanner = new Scanner(nextLine)) {
            scanner.useDelimiter("");
            return parseList(scanner);
        }
    }

    private static PacketList parseList(Scanner scanner) {
        PacketList result = new PacketList(new ArrayList<>());
        StringBuilder sb = new StringBuilder();
        while (scanner.hasNext()) {
            String next = scanner.next();
            if ("[".equals(next)) {
                result.add(parseList(scanner));
            } else if ("]".equals(next)) {
                sb = flushBufferedAndReset(result, sb);
                return result;
            } else if (",".equals(next)) {
                sb = flushBufferedAndReset(result, sb);
            } else {
                sb.append(next);
            }
        }
        return result;
    }

    private static StringBuilder flushBufferedAndReset(PacketList result, StringBuilder sb) {
        String bufferedString = sb.toString().trim();
        if (!bufferedString.isBlank()) {
            result.add(new Packet.Value(Integer.parseInt(bufferedString)));
            sb = new StringBuilder();
        }
        return sb;
    }

    private static boolean isRightOrder(Packet packet1, Packet packet2) {
        return packet1.compareTo(packet2) <= 0;
    }
}

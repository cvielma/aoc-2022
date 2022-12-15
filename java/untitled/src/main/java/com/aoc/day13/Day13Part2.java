package com.aoc.day13;

import com.aoc.day13.Packet.PacketList;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Day13Part2 {

    private static final String test1 = "day13/day13-sample.txt";
    private static final String test2 = "day13/day13-a.txt";

    public static void main(String[] args) throws URISyntaxException, FileNotFoundException {

        File input = new File(Day13Part2.class.getClassLoader().getResource(test2).toURI());
        List<Packet> packets = new ArrayList<>();
        Packet delimiter1 = new PacketList(List.of(new PacketList(List.of(new Packet.Value(2)))));
        Packet delimiter2 = new PacketList(List.of(new PacketList(List.of(new Packet.Value(6)))));
        packets.add(delimiter1);
        packets.add(delimiter2);
        try (Scanner scanner = new Scanner(input)) {
            while (scanner.hasNext()) {
                String nextLine = scanner.nextLine();
                if (nextLine.isBlank()) {
                    continue;
                }
                packets.add(parsePacket(nextLine));
                }
        }

        Collections.sort(packets);

        System.out.println("============ PACKETS:");
        System.out.println(packets.stream().map(Packet::toString).collect(Collectors.joining("\n")));

        int index1 = packets.indexOf(delimiter1) + 1;
        int index2 = packets.indexOf(delimiter2) + 1;

        System.out.println("Index1: " + index1 + ", index2: " + index2 + ". Total: " + (index1 * index2));

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
}

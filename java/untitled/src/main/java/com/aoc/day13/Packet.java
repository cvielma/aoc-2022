package com.aoc.day13;

import java.util.List;

public interface Packet extends Comparable<Packet>{

    record Value(int value) implements Packet {
        PacketList toPacketList() {
            return new PacketList(List.of(this));
        }

        @Override
        public String toString() {
            return "" + value;
        }
    }

    record PacketList(List<Packet> packetList) implements Packet {

        void add(Packet packet) {
            this.packetList.add(packet);
        }

        @Override
        public String toString() {
            return packetList.toString();
        }
    }

    @Override
    default int compareTo(Packet o) {

        System.out.println(" - Compare: " + this + " vs " + o);
        if (this instanceof Value && o instanceof Value) {
            return ((Value) this).value - ((Value) o).value;
        }

        if (this instanceof PacketList && o instanceof PacketList) {
            PacketList that = ((PacketList) this);
            PacketList other = ((PacketList) o);
            for (int i = 0; i < Math.min(that.packetList.size(), other.packetList.size()); i++) {
                Packet thatPacket = that.packetList.get(i);
                Packet otherPacket = other.packetList.get(i);

                int comparisonResult = thatPacket.compareTo(otherPacket);
                System.out.println("    - " + (comparisonResult <= 0 ? "Left is smaller, in right order." : "Right is smaller, NOT in right order.") + " (left: " + thatPacket + ", right: " + otherPacket + ")");
                if (comparisonResult != 0) {
                    return comparisonResult;
                }
            }
            return that.packetList().size() - other.packetList().size();
        }

        if (this instanceof Value && o instanceof PacketList) {
            PacketList converted = ((Value) this).toPacketList();
            System.out.println("   - Mixed types; convert left to " + converted + " and retry comparison");
            return converted.compareTo(o);
        }

        if (this instanceof PacketList && o instanceof Value) {
            PacketList converted = ((Value) o).toPacketList();
            System.out.println("   - Mixed types; convert right to " + converted + " and retry comparison");
            return this.compareTo(converted);
        }

        return 0; // Maybe throw an error
    }
}

package main

import (
    "bufio"
    "fmt"
    "os"
	"strings"
)

type Pair struct {
    a string;
	b string
}

func splitCompartments(allItems string) Pair {
	size := len(allItems) / 2

	return Pair{allItems[0:size], allItems[size:]}
}

func findDuplicates(compartment1 string, compartment2 string) map[string]bool {
	found1 := make(map[string]bool)

	for i := 0; i < len(compartment1); i++ {
		found1[string(compartment1[i])] = true
	}

	duplicates := make(map[string]bool)

	for i:= 0; i < len(compartment2); i++ {
		if (found1[string(compartment2[i])]) {
			duplicates[string(compartment2[i])] = true
		}
	}

	return duplicates
}

func getPriority(duplicates map[string]bool) int {
	totalPrio := 0
	for key, _ := range duplicates {
		if (strings.ToUpper(key) == key) {
			totalPrio = totalPrio + int(key[0]) - int('A') + 27
		} else {
			totalPrio = totalPrio + (int(key[0]) - int('a') + 1)
		}
	}
	return totalPrio
}

func main() {
    scanner := bufio.NewScanner(os.Stdin)
	totalPriority := 0
    for {
        scanner.Scan()
        text := scanner.Text()
        if len(text) != 0 {
            compartments := splitCompartments(text)
			duplicates := findDuplicates(compartments.a, compartments.b)
			priority := getPriority(duplicates)

			totalPriority = totalPriority + priority

        } else {
            break
        }

    }
    // Use collected inputs
    fmt.Println("Result: ", totalPriority)
}
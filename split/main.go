package main

import (
	"bufio"
	"encoding/json"
	"fmt"
	"io/ioutil"
	"log"
	"os"
	"strconv"
	"strings"
)

func main() {
	inputBytes, err := ioutil.ReadAll(bufio.NewReader(os.Stdin))
	if err != nil {
		log.Panicln("Error reading stdin:", err.Error())
	}

	input := strings.Trim(string(inputBytes), "\n\t ")
	n, err := strconv.ParseUint(string(input), 10, 64)
	if err != nil {
		log.Panicln("Error parsing stdin as uint64:", err.Error())
	}

	positions := make([]int, n)
	for i := range positions {
		positions[i] = i + 1
	}

	output, err := json.Marshal(positions)
	if err != nil {
		log.Panicln("Error generating JSON output:", err.Error())
	}

	fmt.Println(string(output))
}

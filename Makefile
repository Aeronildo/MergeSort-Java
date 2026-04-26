JC      = javac
JVM     = java
FLAGS   = -ea
SRC     = StdIn.java StdOut.java Merge.java
INPUT   = Words3.txt

.PHONY: all run clean

all: $(SRC:.java=.class)

StdIn.class: StdIn.java
	$(JC) StdIn.java

StdOut.class: StdOut.java
	$(JC) StdOut.java

Merge.class: Merge.java StdIn.class StdOut.class
	$(JC) Merge.java

run: Merge.class
	$(JVM) $(FLAGS) Merge < $(INPUT)

clean:
	rm -f *.class

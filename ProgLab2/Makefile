FILES = src/Main.java
CLSS = Main


compile:
	mkdir -p bin
	javac -cp src/:lib/Pokemon.jar -d bin $(FILES)
run:
	java -cp bin/:lib/Pokemon.jar $(CLSS)
archive:
	jar -cmf bin/Manifest.mf app.jar -C bin .
RunArch:
	java -jar app.jar

## Use command line

<p>Have problems with adding libs and env to jar. To solve this problem was made the decision to start working with Maven to compile/build project.</p>

to compile Server use:
```bash
javac -cp Server/src:Server/src/lib/commons-lang3-3.12.0.jar:Server/src/lib/opencsv-5.9.jar -d bin Server/src/*.java
```
to build Server.jar use:
```bash
jar cfm Server.jar Server/MANIFEST.MF -C bin . 
```
to build ServerJDK17.jar for helios use:
```bash
jar cfm ServerJDK17.jar bin/MANIFEST.MF .
```


## Helios. Uploading, connecting, running  

<p>When we have ready jar we must start it on Helios</p>

So firstly we must upload jar, but don't forget about collection file and also upload it with using this form:
```bash
scp -P 2222 /Users/macbook/Programming/ProgLab6/Server/Server.jar s408116@helios.cs.ifmo.ru:/home/studs/s408116/Server.jar
```

Next step will be connecting with Helios by port 45000
```bash
ssh -p 2222 -l s408116 -L45000:localhost:45000 helios.cs.ifmo.ru
```

Finally, we ready to start the server!
```bash
java -jar Server.jar
```

## Docker build and run
to build the Docker image use:
```bash
cd Server
docker build -t server-app:latest .
```
to run the image use:
```bash
 docker run -p 45000:45000 server-app
```
to delete all containers use:
```bash
docker rm $(docker ps -q -a)
```

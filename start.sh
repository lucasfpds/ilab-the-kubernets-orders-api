export CLASSPATH=target/api.orders-0.0.1-SNAPSHOT.jar
export className=Application
echo "## Running $className..."
shift
mvn exec:java -Dexec.mainClass="br.com.api.orders.$className"
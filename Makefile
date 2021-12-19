build:
	$(MAKE) -C sweng-app build
run:
	java -cp ./sweng-app/target/sweng-app-1.0-SNAPSHOT.jar com.sweng.app.App

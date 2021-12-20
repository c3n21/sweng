build:
	$(MAKE) -C sweng-app build
test:
	$(MAKE) -C sweng-app test
run:
	java -cp ./sweng-app/target/sweng-app-1.0-SNAPSHOT.jar com.sweng.app.App

run-dist:
	./app/build/install/app/bin/app
	
report:
	./app/gradlew jacocoTestReport		
	
.PHONY: build

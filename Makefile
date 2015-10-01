.PHONY: build

build:
	@docker build -t docker.apiumtech.io/example-automated-project .

run:
	@node src/main.js

GRADLE=./gradlew
MODULES=$(shell ls -d */ | grep app)

# Commands
.PHONY: all clean build test run help

all: build

clean:
	$(GRADLE) clean
	@echo "Cleaned all build files."

.PHONY: pipeline
pipeline: all

build:
	$(GRADLE) \
		build \
		:app-h2-db-connection:build
	@echo "Built modules."

test:
	$(GRADLE) test
	@echo "Tested the entire project."

run:
	$(GRADLE) :app-h2-db-connection:run
	@echo "Running the h2 application."

help:
	@echo "Usage:"
	@echo "  make all         - Build and test all modules"
	@echo "  make clean       - Clean all build files"
	@echo "  make build       - Build all modules"
	@echo "  make test        - Test all modules"
	@echo "  make run         - Run the root application"
	@echo "  make run-module  - Build and test a specific module"
	@echo "  make help        - Show this help message"

-include .User.mk
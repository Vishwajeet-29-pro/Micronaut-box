GRADLE=./gradlew
MODULES=$(shell ls -d * | grep app)

# Commands
.PHONY: all clean build test run help

all: build

clean:
	$(GRADLE) clean
	@echo "Cleaned all build files."

.PHONY: pipeline
pipeline: all

build:
	$(GRADLE) build $(foreach MODULE,$(MODULES),:$(MODULE):build)
	@echo "Built all modules: $(MODULES)"

test:
	$(GRADLE) test
	@echo "Tested the entire project."

run-h2:
	$(GRADLE) :app-h2-db-connection:run
	@echo "Running the h2 application."

run-postgres:
	$(GRADLE) :app-postgres-db-connection

run-simple-impl:
	$(GRADLE) :app-simple-micronaut-impl:run
	@echo "Running the simple application."

# Build and test a specific module
build-module:
	@read -p "Enter the module name (e.g., db-h2-connection): " MODULE && \
	$(GRADLE) :$$MODULE:clean :$$MODULE:build :$$MODULE:test && \
	echo "Built and tested $$MODULE."

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
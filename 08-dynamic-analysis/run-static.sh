#!/bin/bash

mvn package
java -cp target/demoda-1.0-SNAPSHOT.jar ca.uwaterloo.cs846.StaticAnalyzer $PWD/target/classes/ca/uwaterloo/cs846/exp/

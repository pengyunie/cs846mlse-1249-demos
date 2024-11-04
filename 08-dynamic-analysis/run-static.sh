#!/bin/bash

mvn compile
mvn exec:java -Dexec.mainClass="ca.uwaterloo.cs846.StaticAnalyzer" -Dexec.args="$PWD/target/classes/ca/uwaterloo/cs846/exp"

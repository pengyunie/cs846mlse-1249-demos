#!/bin/bash

mvn package
java -javaagent:target/demoda-1.0-SNAPSHOT.jar -cp target/demoda-1.0-SNAPSHOT.jar ca.uwaterloo.cs846.exp.ShapeMain

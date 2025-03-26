#!/bin/bash

javac -d classes src/bankapp/*.java 

java -cp classes bankapp.Main
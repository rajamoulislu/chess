@echo off
cls
javac -sourcepath . Main/*.java Enums/*.java pieces/*.java
java Main.Main
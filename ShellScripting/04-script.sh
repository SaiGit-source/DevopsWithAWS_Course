#! /bin/bash

echo "Enter first number: "

read NUM1

echo "Enter second number: "

read NUM2

if [ $NUM1 -eq $NUM2 ]; then
        echo "Numbers are equal"
else
        echo "Numbers are not equal"

fi

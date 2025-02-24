#! /bin/bash

echo "Enter number: "

read NUM1

if [ $NUM1 -gt 0 ]; then
        echo "Positive number"
elif [ $NUM1 -lt 0 ]; then
        echo "Negative number"
else
        echo "Number is zero"
fi


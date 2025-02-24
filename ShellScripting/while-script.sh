#! /bin/bash

echo "Enter number: "

read NUM

while [ $NUM -le 6 ]
 do
         echo "$NUM"
         let NUM++
done

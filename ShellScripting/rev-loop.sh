#! /bin/bash

echo "Enter Number: "
read NUM

while [ $NUM -gt 0 ]
 do
         echo "$NUM"
         let NUM--
done
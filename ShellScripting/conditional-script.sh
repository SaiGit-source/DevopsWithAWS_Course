#! /bin/bash

echo "Check Eligiblity"
echo "Enter Age: "

read AGE

if [ $AGE -gt 18 ]; then
        echo "Eligible to work"
else
        echo "Not Eligible to work"
fi

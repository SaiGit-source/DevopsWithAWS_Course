#! /bin/bash

echo "Enter number: "

read NUM
flag=0

for (( i=2; i<$NUM; i++));
  do
          if [ $((NUM % i)) -eq 0 ]; then

          echo "Not a Prime"
          flag=1
          break
          fi
  done

  if [ $flag -eq 0 ]; then

        echo "Prime"
  fi

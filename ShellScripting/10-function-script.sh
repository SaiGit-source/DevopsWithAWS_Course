#! /bin/bash

function doTask(){
        echo "Enter filename: "

        read FILENAME

        cat $FILENAME
}

doTask

#! /bin/bash

function fileManager(){
        echo "Enter filename: "

        read FILE

        if [ -f "$FILE" ]; then
                echo "File exists and content is printed below"
                cat $FILE
        else
                echo "File doesn't exist hence creating a new file"
                touch $FILE
                echo "File $FILE is created"
        fi
}

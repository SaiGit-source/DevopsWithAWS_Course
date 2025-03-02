#! /bin/bash

PROJECT_NAME="alienapp"
ROOT_DIR=$(pwd)


create_project(){

        mkdir $1
        mkdir $1/src
        mkdir $1/tests
        mkdir $1/docs
        mkdir $1/config
        touch $1/config/config.yaml
        touch $1/docs/README.md
        touch $1/src/app.js
        touch $1/src/main.css
        touch $1/src/index.html
        touch $1/tests/test.js
}

create_project $ROOT_DIR/$PROJECT_NAME
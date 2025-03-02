#! /bin/bash

# Define log file path
LOG_FILE=myapp.log

#function to log messages

log_message(){
        local timestamp=$(date +"%Y-%m-%d %T")
        local message=$1
        echo "[$timestamp] $message" >> $LOG_FILE
}

# Call log function
log_message "Script Execution Started"

echo "This is something regular message - 01"
echo "This is something regular message - 02"

# Simulate error
mkdirs java

# call log function once again

log_message "Script Execution Completed"

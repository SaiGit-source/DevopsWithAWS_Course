#! /bin/bash

# Define log file path
LOG_FILE=myapp.log

# redirect error messages to  log file
# 2 means only error messages
exec 2>> $LOG_FILE

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
ls abcd

# call log function once again

log_message "Script Execution Completed"

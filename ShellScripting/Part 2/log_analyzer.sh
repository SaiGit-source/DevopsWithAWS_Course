#! /bin/bash

# Count total error message occurences

LOG_FILE=/var/log/messages

ERROR_COUNT=$(sudo grep -c "ERROR" "$LOG_FILE")

# Printing count of error messages
#
echo "Number of errors in system log file : $ERROR_COUNT"

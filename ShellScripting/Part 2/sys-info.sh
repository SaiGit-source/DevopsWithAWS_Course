#! /bin/bash

# this Script is used to print System information
#
# Display current Date and Time

echo "Date & Time : $(date)"

# Display hostname of the system
echo "Hostname : $(hostname)"

# Display uptime of System
echo "System Uptime : $(uptime)"


# Display Disk usage
echo "Disk usage: $(df -h)"


# Display memory usage
echo "Memory usage : $(free -h)"


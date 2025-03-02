#! /bin/bash

SOURCE_DIR=/home/ec2-user
TARGET_DIR=/home/ec2-user

echo "Backup process starting..."

tar -czvf "$SOURCE_DIR/backup_$(date +%Y%m%d).tar.gz" "$TARGET_DIR"

echo "Backup is completed with a tar file"
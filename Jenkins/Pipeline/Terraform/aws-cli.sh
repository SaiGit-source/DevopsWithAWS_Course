#!/bin/bash

set -e

echo "🔄 Updating system packages..."
sudo apt-get update -y
sudo apt-get install -y unzip curl

echo "📥 Downloading AWS CLI v2..."
curl "https://awscli.amazonaws.com/awscli-exe-linux-x86_64.zip" -o "awscliv2.zip"

echo "📦 Unzipping..."
unzip -q awscliv2.zip

echo "🚀 Installing AWS CLI..."
sudo ./aws/install

echo "🧹 Cleaning up..."
rm -rf aws awscliv2.zip

echo "✅ Verifying AWS CLI installation..."
aws --version

echo "🎉 AWS CLI installed successfully!"

